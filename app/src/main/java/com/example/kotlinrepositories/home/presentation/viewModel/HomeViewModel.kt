package com.example.kotlinrepositories.home.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinrepositories.home.domain.entity.HomeEntity
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {
    var items: MutableLiveData<List<HomeEntity>> = MutableLiveData()
    var page: Int = 1

    init {
        useCase.getKotlinRepositories(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                this.items.value = it
            }, {
                it.printStackTrace()
            })
    }
}