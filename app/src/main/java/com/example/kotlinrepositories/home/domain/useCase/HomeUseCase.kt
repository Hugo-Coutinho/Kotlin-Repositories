package com.example.kotlinrepositories.home.domain.useCase

import com.example.kotlinrepositories.home.data.remote.SortType
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Observable

interface HomeUseCase {
    fun getKotlinRepositories(page: Int, sortType: SortType): Observable<HomeRepositoryEntity>
}

class HomeUseCaseImpl(private val repository: HomeRepository): HomeUseCase {
    override fun getKotlinRepositories(page: Int, sortType: SortType): Observable<HomeRepositoryEntity> {
         return repository.getKotlinRepositoriesFromApi(page, sortType)
    }
}

