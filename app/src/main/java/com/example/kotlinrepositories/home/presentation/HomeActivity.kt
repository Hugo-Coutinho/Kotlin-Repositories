package com.example.kotlinrepositories.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import com.example.kotlinrepositories.home.presentation.view.HomeListingRepositoriesFragment
import org.koin.android.ext.android.inject


class HomeActivity: AppCompatActivity() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private val useCase: HomeUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.add(this, R.id.home_fragment_container, HomeListingRepositoriesFragment())
    }
}