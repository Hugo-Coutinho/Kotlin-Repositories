package com.example.kotlinrepositories.home.data.remote

import com.example.kotlinrepositories.home.data.model.Item
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

enum class SortType(val rawValue: String) {
    FORK("forks"),
    STAR("stars")
}

interface IGithubApi {
    @GET("search/repositories?q=language:kotlin")
    fun findKotlinRepositories(@Query("page") page: Int, @Query("sort") sortType: String): Observable<Item>
}

interface KotlinRepositoriesRemoteDataSource {
    fun getKotlinRepositories(page: Int, sortType: SortType): Observable<KotlinRepositoriesModel>
}

class KotlinRepositoriesRemoteDataSourceImpl(private val client: IGithubApi): KotlinRepositoriesRemoteDataSource {
    override fun getKotlinRepositories(page: Int, sortType: SortType): Observable<KotlinRepositoriesModel> {
        Logger.w("doing request to api")
    return client.findKotlinRepositories(page, sortType.rawValue)
        .flatMap { item -> Observable.just(item.items) }
    }
}