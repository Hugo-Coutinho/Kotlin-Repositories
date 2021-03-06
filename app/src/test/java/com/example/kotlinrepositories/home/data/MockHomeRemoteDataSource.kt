package com.example.kotlinrepositories.home.data

import com.example.kotlinrepositories.core.di.provideRetrofit
import com.example.kotlinrepositories.home.data.model.Item
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesElement
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.example.kotlinrepositories.home.data.model.Owner
import com.example.kotlinrepositories.home.data.remote.IGithubApi
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSourceImpl
import io.reactivex.rxjava3.core.Observable
import org.mockito.Mock
import org.mockito.Mockito

object MockHomeRemoteDataSource {

    @Mock
    private lateinit var client: IGithubApi

    fun getKotlinRepositoriesRemoteDataSource(): KotlinRepositoriesRemoteDataSource {
        this.client =  Mockito.mock(provideRetrofit().create(IGithubApi::class.java)::class.java)
        return KotlinRepositoriesRemoteDataSourceImpl(this.client)
    }

    fun getMockKotlinRepositories(): Observable<KotlinRepositoriesModel> {
        return Observable.just(KotlinRepositoriesModel(listOf(this.mockModel())))
    }

    fun didClientResponse(): Observable<Item> {
        return Observable.just(this.mockItem())
    }

    private fun mockItem(): Item {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        val model = KotlinRepositoriesElement("architecture-samples", false, owner, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.","https://github.com/google/flexbox-webview_repository_page",36642,10175)
        return Item(KotlinRepositoriesModel(listOf(model)))
    }

    private fun mockModel(): KotlinRepositoriesElement {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        return KotlinRepositoriesElement("architecture-samples", false, owner, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.","https://github.com/google/flexbox-webview_repository_page",36642,10175)
    }
}