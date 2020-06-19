package com.example.kotlinrepositories.home.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase

class ViewModelFactory(private var useCase: HomeUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(useCase) as T
    }

}