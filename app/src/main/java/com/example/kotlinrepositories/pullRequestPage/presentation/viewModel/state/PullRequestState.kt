package com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.state

import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity

abstract class PullRequestState

class PRSuccessState(val items: PullEntity): PullRequestState()

class PRErrorState(val message: String): PullRequestState()

class PRLoadingState: PullRequestState()