package com.example.kotlinrepositories.pullRequestPage.domain.repository

import com.example.kotlinrepositories.pullRequestPage.data.model.PullElement
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntityElement
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable

interface PullRequestRepository {
    fun getPullsFromApi(user: String, repo: String): Observable<PullEntity>
}

class PullRequestRepositoryImpl(private val remoteDataSource: PullRequestRemoteDataSource): PullRequestRepository {
    override fun getPullsFromApi(user: String, repo: String): Observable<PullEntity> {
        Logger.w("parsing model to entity from data source")
        return remoteDataSource.getPullRequest(user, repo)
            .flatMap { items -> Observable.just(ArrayList(items.map { model -> this.parseModelToEntity(model) })) }
    }

    private fun parseModelToEntity(model: PullElement): PullEntityElement {
        return PullEntityElement(model.user.login, model.title, model.body, model.user.avatarURL)
    }
}