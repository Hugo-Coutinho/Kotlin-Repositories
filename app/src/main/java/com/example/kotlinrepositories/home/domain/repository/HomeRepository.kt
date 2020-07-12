package com.example.kotlinrepositories.home.domain.repository

import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.SortType
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntityElement
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getKotlinRepositoriesFromApi(page: Int, sortType: SortType): Observable<HomeRepositoryEntity>
}

class HomeRepositoryImpl(private val remoteDataSource: KotlinRepositoriesRemoteDataSource): HomeRepository {
    override fun getKotlinRepositoriesFromApi(page: Int, sortType: SortType): Observable<HomeRepositoryEntity> {
        Logger.w("parsing model to entity from data source")
        return remoteDataSource.getKotlinRepositories(page, sortType)
            .flatMap { items -> Observable.just(ArrayList(items.map { model -> HomeRepositoryEntityElement.toEntity(model) })) }
    }
}