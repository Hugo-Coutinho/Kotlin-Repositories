package com.example.kotlinrepositories.home.domain.useCase

import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Observable

interface HomeUseCase {
fun getKotlinRepositories(page: Int): Observable<ArrayList<HomeRepositoryEntity>>
}

class HomeUseCaseImpl(private val repository: HomeRepository): HomeUseCase {
    override fun getKotlinRepositories(page: Int): Observable<ArrayList<HomeRepositoryEntity>> {
         return repository.getKotlinRepositoriesFromApi(page)
    }
}

