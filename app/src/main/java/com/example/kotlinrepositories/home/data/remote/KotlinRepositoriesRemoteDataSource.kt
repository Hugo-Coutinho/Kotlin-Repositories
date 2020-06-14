package com.example.kotlinrepositories.home.data.remote

import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IGithubApi {
    @GET("search/repositories")
    fun findKotlinRepositories(@Query("q") query: String,
                           @Query("page") page: Int): Observable<KotlinRepositoriesModel>
}

interface KotlinRepositoriesRemoteDataSource {
    fun getKotlinRepositories(page: Int): Observable<KotlinRepositoriesModel>
}

class KotlinRepositoriesRemoteDataSourceImpl(private val client: IGithubApi): KotlinRepositoriesRemoteDataSource {
    override fun getKotlinRepositories(page: Int): Observable<KotlinRepositoriesModel> {
    return client.findKotlinRepositories(Constant.queryKotlinLanguage, page).flatMap { items -> Observable.just(items) }
    }
}