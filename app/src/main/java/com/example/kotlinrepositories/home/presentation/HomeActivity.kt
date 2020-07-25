package com.example.kotlinrepositories.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.core.util.layout.ColorEnum
import com.example.kotlinrepositories.core.util.layout.LayoutHelper
import com.example.kotlinrepositories.core.view.ErrorFragment
import com.example.kotlinrepositories.core.view.LoadingFragment
import com.example.kotlinrepositories.home.presentation.view.HomeListingRepositoriesFragment
import com.example.kotlinrepositories.home.presentation.view.HomeSortView
import com.example.kotlinrepositories.home.presentation.view.IHomeSort
import com.example.kotlinrepositories.home.presentation.viewModel.HomeViewModel
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeErrorState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeLoadingState
import com.example.kotlinrepositories.home.presentation.viewModel.state.HomeSuccessState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_header_layout.view.*
import org.koin.android.ext.android.inject


class HomeActivity: AppCompatActivity() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private val viewModel: HomeViewModel by inject()
    private var homeSortView: IHomeSort? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.homeActivityInitialization()
        this.observeHomeState()
    }

    private fun homeActivityInitialization() {
        this.homeSortView = HomeSortView(view_home_sort, view_home_sort_header.iv_home_header_arrow, this.viewModel)
        this.listenerTotalItemsToUpdateHeaderResults()
        this.setupHeaderBottomLine()
        this.sortListener()
        this.arrowRotate()
    }

    private fun sortListener() {
        view_home_sort_header.tv_home_filter.setOnClickListener {
            this.homeSortView?.updateVisibility()
        }
    }

    private fun arrowRotate() {
        view_home_sort_header.iv_home_header_arrow.animate().rotationBy(180f).start()
    }

    private fun observeHomeState() {
        this.viewModel.viewModelData.currentState.observe(this, Observer {
            when (it) {
                is HomeSuccessState -> {
                    this.headerResultsCountInitialization(it.items.count())
                    fragmentManager.replace(this, R.id.home_fragment_container, HomeListingRepositoriesFragment(this.viewModel, it.items))
                }

                is HomeErrorState -> {
                    fragmentManager.replace(this, R.id.home_fragment_container, ErrorFragment(it.message))
                }

                is HomeLoadingState -> {
                    fragmentManager.replace(this, R.id.home_fragment_container, LoadingFragment())
                }
            }
        })
    }

    private fun setupHeaderBottomLine() {
        view_home_sort_header.background = LayoutHelper.getBottomLineSetup(ColorEnum.BLACK)
    }

    private fun headerResultsCountInitialization(totalItems: Int) {
         view_home_sort_header.tv_home_results.text = "$totalItems Results"
    }


    private fun listenerTotalItemsToUpdateHeaderResults() {
        this.viewModel.viewModelData.vmItems.observe(this, Observer {
            view_home_sort_header.tv_home_results.text = "${ it.count() } Results"
        })
    }
}