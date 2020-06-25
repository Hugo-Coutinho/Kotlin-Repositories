package com.example.kotlinrepositories.home.domain.repository

import com.example.kotlinrepositories.home.data.MockHomeRemoteDataSource
import com.example.kotlinrepositories.home.data.model.Owner
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import io.reactivex.rxjava3.core.Observable
import org.mockito.Mock
import org.mockito.Mockito

object MockHomeRepository {

    @Mock
    private lateinit var remoteDataSource: KotlinRepositoriesRemoteDataSource

    fun getMockRepository(): HomeRepository {
        this.remoteDataSource =  Mockito.mock(MockHomeRemoteDataSource.getKotlinRepositoriesRemoteDataSource()::class.java)
        return HomeRepositoryImpl(this.remoteDataSource)
    }

    fun getMockKotlinRepositories(): Observable<ArrayList<HomeRepositoryEntity>> {
        return Observable.just(ArrayList(listOf(this.mockEntity())))
    }

    private fun mockEntity(): HomeRepositoryEntity {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        return HomeRepositoryEntity("architecture-samples",false, owner.login, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.","https://github.com/google/flexbox-webview_repository_page","" , 36642,10175)
    }
}