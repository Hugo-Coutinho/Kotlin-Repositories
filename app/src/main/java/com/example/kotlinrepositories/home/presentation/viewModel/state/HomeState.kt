package com.example.kotlinrepositories.home.presentation.viewModel.state

import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity

abstract class HomeState

class HomeSuccessState(val items: HomeRepositoryEntity): HomeState()

class HomeErrorState(val message: String): HomeState()

class HomeLoadingState: HomeState()