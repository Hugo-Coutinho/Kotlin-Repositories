package com.example.kotlinrepositories.pullRequestPage.data.remote

import com.example.kotlinrepositories.pullRequestPage.data.model.PullModel
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface IGithubPullsApi {
    @GET("repos/{owner}/{repo}/pulls")
    fun findPullRequest(@Query("owner") userName: String, @Query("repo") repository: String): Observable<PullModel>
}

interface PullRequestRemoteDataSource {
    fun getPullRequest(userName: String, repository: String): Observable<PullModel>
}


class PullRequestRemoteDataSourceImpl(private val client: IGithubPullsApi): PullRequestRemoteDataSource {
    override fun getPullRequest(userName: String, repository: String): Observable<PullModel> {
        Logger.w("doing request to api to get kotlin pull requests")
        return client.findPullRequest(userName, repository)
            .flatMap { Observable.just(it) }
    }
}