package com.example.kotlinrepositories.home.domain.repository

import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.domain.entity.HomeEntity
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getKotlinRepositoriesFromApi(page: Int): Observable<List<HomeEntity>>
}

class HomeRepositoryImpl(private val remoteDataSource: KotlinRepositoriesRemoteDataSource): HomeRepository {
    override fun getKotlinRepositoriesFromApi(page: Int): Observable<List<HomeEntity>> {
        Logger.w("[HomeRepository] - parsing model to entity from data source")
        return remoteDataSource.getKotlinRepositories(1)
            .flatMap { items -> Observable.just(items.map { model -> this.parseModelToEntity(model) }) }
//            .flatMap { items -> Observable.just(items) }
    }

    private fun parseModelToEntity(model: KotlinRepositoriesModel): HomeEntity {
        return HomeEntity(model.name, model.private, model.owner.login, model.description, model.html_url, model.stargazers_count, model.forks_count)
    }
}