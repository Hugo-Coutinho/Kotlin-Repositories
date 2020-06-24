package com.example.kotlinrepositories.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.core.view.LoadingFragment
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import com.example.kotlinrepositories.home.presentation.view.HomeErrorFragment
import com.example.kotlinrepositories.home.presentation.view.HomeListingRepositoriesFragment
import com.example.kotlinrepositories.home.presentation.viewModel.HomeViewModel
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeErrorState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeSuccessState
import org.koin.android.ext.android.inject


class HomeActivity: AppCompatActivity() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private val useCase: HomeUseCase by inject()
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.displayLoadingState()
        this.viewModelInit()
        this.observeHomeState()
    }

    private fun displayLoadingState() {
        fragmentManager.add(this, R.id.home_fragment_container, LoadingFragment())
    }

    private fun viewModelInit() {
        this.viewModel = ViewModelProvider(this, HomeViewModel.ViewModelFactory(this.useCase)).get(HomeViewModel::class.java)
    }

    private fun observeHomeState() {
        this.viewModel.currentState.observe(this, Observer {
            if (it is HomeSuccessState) {
                fragmentManager.replace(this, R.id.home_fragment_container, HomeListingRepositoriesFragment(this.viewModel, it.items))
            } else if (it is HomeErrorState) {
                fragmentManager.replace(this, R.id.home_fragment_container, HomeErrorFragment(it.message))
            }
        })
    }
}