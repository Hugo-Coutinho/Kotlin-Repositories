package com.example.kotlinrepositories.pullRequestPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.constant.Constant.Companion.PULL_ITEM
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.core.util.layout.ColorEnum
import com.example.kotlinrepositories.core.util.layout.LayoutHelper
import com.example.kotlinrepositories.core.view.ErrorFragment
import com.example.kotlinrepositories.core.view.LoadingFragment
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntityElement
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntityElement
import com.example.kotlinrepositories.pullRequestPage.presentation.view.PullEmptyFragment
import com.example.kotlinrepositories.pullRequestPage.presentation.view.PullRequestListFragment
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.PullRequestViewModel
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PREmptyState
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PRErrorState
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PRLoadingState
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state.PRSuccessState
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.pull_request_header_layout.view.*
import org.koin.android.ext.android.inject

class PullActivity: AppCompatActivity() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private val viewModel: PullRequestViewModel by inject()
    private lateinit var currentRepositorySelected: HomeRepositoryEntityElement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        this.preparingActionBar()
        this.bindSelectedRepository()
        this.pullRequestInitialization()
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

    private fun bindSelectedRepository() {
        this.currentRepositorySelected = intent.getParcelableExtra(PULL_ITEM)
    }

    private fun pullRequestInitialization() {
        this.viewModel.getPulls(this.currentRepositorySelected.userName, this.currentRepositorySelected.repositoryName)
    }

    private fun observePRState() {
        this.viewModel.currentState.observe(this, Observer {
            when (it) {
                is PRSuccessState -> {
                    this.configureHeader(items = it.items)
                    fragmentManager.replace(this, R.id.pr_fragment_container, PullRequestListFragment(this.viewModel, it.items))
                }
                is PRErrorState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, ErrorFragment(it.message))
                }
                is PREmptyState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, PullEmptyFragment())
                }
                is PRLoadingState -> {
                    fragmentManager.replace(this, R.id.pr_fragment_container, LoadingFragment())
                }
            }
        })
    }

    private fun configureHeader(items: PullEntity) {
        val prOpenedCount = items.filter { it.pullState.equals(PullEntityElement.PullStateEnum.OPENED) }.count()
        val prClosedCount = items.filter { it.pullState.equals(PullEntityElement.PullStateEnum.CLOSED) }.count()

        view_pr_header.visibility = View.VISIBLE
        view_pr_header.tv_pr_opened.text = "$prOpenedCount opened"
        view_pr_header.tv_pr_closed.text = "/ $prClosedCount closed"
        view_pr_header.background = LayoutHelper.getBottomLineSetup(ColorEnum.BLACK)
    }
}