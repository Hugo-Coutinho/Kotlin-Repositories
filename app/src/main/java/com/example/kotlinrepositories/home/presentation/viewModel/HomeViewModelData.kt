package com.example.kotlinrepositories.home.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.home.data.remote.SortType
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeErrorState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeLoadingState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeSuccessState

class HomeViewModelData(
    var currentState: MutableLiveData<HomeState>,
    var vmItems: MutableLiveData<HomeRepositoryEntity>,
    var currentSortType: SortType,
    private var vmTotalItems: HomeRepositoryEntity
) {

    fun setLoadingState() {
        this.currentState.postValue(HomeLoadingState())
    }

    fun setErrorState() {
        this.currentState.postValue(HomeErrorState(Constant.HomeErrorMessage))
    }

    fun successInfiniteScroll(nextItems: HomeRepositoryEntity) {
        this.vmTotalItems.addAll(nextItems)
        this.vmItems.postValue(this.vmTotalItems)
    }

    fun successFetchRepositories(nextItems: HomeRepositoryEntity) {
        this.vmTotalItems.addAll(nextItems)
        this.currentState.postValue(HomeSuccessState(this.vmTotalItems))
    }

    fun successFetchRepositoriesByPageAndSortType(nextItems: HomeRepositoryEntity, sortType: SortType) {
        this.currentSortType = sortType
        this.vmTotalItems = nextItems
        this.currentState.postValue(HomeSuccessState(nextItems))
    }
}