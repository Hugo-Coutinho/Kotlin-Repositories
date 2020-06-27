package com.example.kotlinrepositories.pullRequestPage.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.useCase.PullRequestUseCase
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.*
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.schedulers.Schedulers

class PullRequestViewModel(private val useCase: PullRequestUseCase, private val userName: String, private val repositoryName: String): ViewModel() {
    var currentState: MutableLiveData<PullRequestState> = MutableLiveData()

    init {
        Logger.i("initializing viewModel with loading state")
        this.currentState.value = PRLoadingState()
        this.didInitGetPulls()
    }

    private fun didInitGetPulls() {
        Logger.i("requesting pull requests by page $repositoryName")
        useCase.gePulls(userName, repositoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                this.updateState(it)
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
                this.currentState.postValue(PRErrorState(Constant.HomeErrorMessage))
            })
    }

    private fun updateState(items: PullEntity) {
        if (items.isEmpty()) {
            Logger.i("This repository has no pull requests")
            this.currentState.postValue(PREmptyState())
        } else {
            Logger.i("success parse for entity with ${items.count()} pulls")
            this.currentState.postValue(PRSuccessState(items))
        }
    }

    class ViewModelFactory(private var useCase: PullRequestUseCase, private val userName: String, private val repositoryName: String): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PullRequestViewModel(useCase, userName, repositoryName) as T
        }
    }
}