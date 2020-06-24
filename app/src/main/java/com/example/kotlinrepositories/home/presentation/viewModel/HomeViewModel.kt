package com.example.kotlinrepositories.home.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.home.data.model.Owner
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeErrorState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeLoadingState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeSuccessState
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {
    var currentState: MutableLiveData<HomeState> = MutableLiveData()
    var vmItems: MutableLiveData<List<HomeRepositoryEntity>> = MutableLiveData()

    init {
        Logger.i("initializing viewModel with loading state")
        this.currentState.value = HomeLoadingState()
        this.didInitGetKotlinRepositories()
    }

    private fun didInitGetKotlinRepositories() {
        Logger.i("requesting repositories by page 1")
        useCase.getKotlinRepositories(1)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.currentState.postValue(HomeSuccessState(it))
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
                this.currentState.postValue(HomeErrorState(Constant.HomeErrorMessage))
            })

    }

    fun fetchKotlinRepositoriesByPage(page: Int) {
        Logger.i("requesting repositories by page $page")
        useCase.getKotlinRepositories(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.vmItems.postValue(it)
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
            })

    }

    class ViewModelFactory(private var useCase: HomeUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(useCase) as T
        }
    }
}