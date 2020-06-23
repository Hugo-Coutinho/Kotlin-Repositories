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
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {
    var currentState: MutableLiveData<HomeState> = MutableLiveData()
    var page: Int = 1

    init {
        this.currentState.value = HomeLoadingState()
        useCase.getKotlinRepositories(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                this.currentState.postValue(HomeSuccessState(it))
            }, {
                this.currentState.postValue(HomeErrorState(Constant.HomeErrorMessage))
            })
    }

    private fun mockEntity(): HomeRepositoryEntity {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        return HomeRepositoryEntity("architecture-samples",false, owner.login, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.",owner.avatar_url,"https://github.com/google/flexbox-layout", 36642,10175)
    }

    class ViewModelFactory(private var useCase: HomeUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(useCase) as T
        }
    }
}