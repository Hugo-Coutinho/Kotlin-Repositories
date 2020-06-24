package com.example.kotlinrepositories.home.domain.repository

import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getKotlinRepositoriesFromApi(page: Int): Observable<ArrayList<HomeRepositoryEntity>>
}

class HomeRepositoryImpl(private val remoteDataSource: KotlinRepositoriesRemoteDataSource): HomeRepository {
    override fun getKotlinRepositoriesFromApi(page: Int): Observable<ArrayList<HomeRepositoryEntity>> {
        Logger.w("parsing model to entity from data source")
        return remoteDataSource.getKotlinRepositories(page)
            .flatMap { items -> Observable.just(ArrayList(items.map { model -> this.parseModelToEntity(model) })) }
    }

    private fun parseModelToEntity(model: KotlinRepositoriesModel): HomeRepositoryEntity {
        return HomeRepositoryEntity(model.name, model.private, model.owner.login, model.description, model.owner.avatar_url, model.html_url, model.stargazers_count, model.forks_count)
    }
}