package com.example.kotlinrepositories.home.data.remote

import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.home.data.model.Item
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IGithubApi {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun findKotlinRepositories(@Query("page") page: Int): Observable<Item>
}

interface KotlinRepositoriesRemoteDataSource {
    fun getKotlinRepositories(page: Int): Observable<ArrayList<KotlinRepositoriesModel>>
}

class KotlinRepositoriesRemoteDataSourceImpl(private val client: IGithubApi): KotlinRepositoriesRemoteDataSource {
    override fun getKotlinRepositories(page: Int): Observable<ArrayList<KotlinRepositoriesModel>> {
        Logger.w("doing request to api")
    return client.findKotlinRepositories(page)
        .flatMap { item -> Observable.just(item.items) }
    }
}