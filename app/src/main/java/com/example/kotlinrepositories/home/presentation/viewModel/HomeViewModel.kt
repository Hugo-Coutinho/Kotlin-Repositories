package com.example.kotlinrepositories.home.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinrepositories.home.data.remote.SortType
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {
    var viewModelData: HomeViewModelData = HomeViewModelData(MutableLiveData(), MutableLiveData(), SortType.STAR, HomeRepositoryEntity())

    init {
        Logger.i("initializing viewModel with loading state")
        this.viewModelData.setLoadingState()
        this.didInitGetKotlinRepositories()
    }

    fun fetchRepositoriesNextPageInfiniteScroll(page: Int) {
        Logger.i("requesting repositories by page $page, sorting for the ${this.viewModelData.currentSortType}")
        useCase.getKotlinRepositories(page, this.viewModelData.currentSortType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.viewModelData.successInfiniteScroll(it)
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
            })
    }

    fun fetchKotlinRepositoriesByPageAndSort(page: Int, sortType: SortType) {
        Logger.i("loading state to sort repositories")
        this.viewModelData.setLoadingState()
        Logger.i("requesting repositories by page $page, sorting for the ${sortType.rawValue}")
        useCase.getKotlinRepositories(page, sortType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.viewModelData.successFetchRepositoriesByPageAndSortType(it, sortType)
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
                this.viewModelData.setErrorState()
            })
    }

    private fun didInitGetKotlinRepositories() {
        Logger.i("requesting repositories by page 1")
        useCase.getKotlinRepositories(1, this.viewModelData.currentSortType)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.viewModelData.successFetchRepositories(it)
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
                this.viewModelData.setErrorState()
            })
    }
}