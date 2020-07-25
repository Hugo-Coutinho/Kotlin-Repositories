package com.example.kotlinrepositories.pullRequestPage.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.useCase.PullRequestUseCase
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.*
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.schedulers.Schedulers

class PullRequestViewModel(private val useCase: PullRequestUseCase): ViewModel() {
    var currentState: MutableLiveData<PullRequestState> = MutableLiveData()

    fun getPulls(userName: String, repositoryName: String) {
        this.currentState.postValue(PRLoadingState())
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
}