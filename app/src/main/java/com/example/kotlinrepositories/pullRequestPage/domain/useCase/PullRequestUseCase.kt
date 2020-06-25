package com.example.kotlinrepositories.pullRequestPage.domain.useCase

import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepository
import io.reactivex.rxjava3.core.Observable

interface PullRequestUseCase {
    fun gePulls(userName: String, RepositoryName: String): Observable<PullEntity>
}

class PullRequestUseCaseImpl(private val repository: PullRequestRepository): PullRequestUseCase {
    override fun gePulls(userName: String, RepositoryName: String): Observable<PullEntity> {
        return repository.getPullsFromApi(userName, RepositoryName)
    }
}
