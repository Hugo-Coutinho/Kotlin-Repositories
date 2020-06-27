package com.example.kotlinrepositories.pullRequestPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.constant.Constant.Companion.PULL_ITEM
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.core.view.ErrorFragment
import com.example.kotlinrepositories.core.view.LoadingFragment
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.pullRequestPage.domain.useCase.PullRequestUseCase
import com.example.kotlinrepositories.pullRequestPage.presentation.view.PullEmptyFragment
import com.example.kotlinrepositories.pullRequestPage.presentation.view.PullRequestListFragment
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.PullRequestViewModel
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PREmptyState
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PRErrorState
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PRSuccessState
import org.koin.android.ext.android.inject

class PullActivity: AppCompatActivity() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private val useCase: PullRequestUseCase by inject()
    private lateinit var viewModel: PullRequestViewModel
    private lateinit var currentRepositorySelected: HomeRepositoryEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        this.preparingActionBar()
        this.displayLoadingState()
        this.bindSelectedRepository()
        this.viewModelInit()
        this.observePRState()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun preparingActionBar() {
        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.title_activity_pull_request)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }


    private fun displayLoadingState() {
        this.fragmentManager.add(this, R.id.pr_fragment_container, LoadingFragment())
    }

    private fun bindSelectedRepository() {
        this.currentRepositorySelected = intent.getParcelableExtra(PULL_ITEM)
    }

    private fun viewModelInit() {
        this.viewModel = ViewModelProvider(this, PullRequestViewModel.ViewModelFactory(this.useCase, this.currentRepositorySelected.userName, this.currentRepositorySelected.repositoryName)).get(PullRequestViewModel::class.java)
    }

    private fun observePRState() {
        this.viewModel.currentState.observe(this, Observer {
            when (it) {
                is PRSuccessState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, PullRequestListFragment(this.viewModel, it.items))
                }
                is PRErrorState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, ErrorFragment(it.message))
                }
                is PREmptyState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, PullEmptyFragment())
                }
            }
        })
    }
}