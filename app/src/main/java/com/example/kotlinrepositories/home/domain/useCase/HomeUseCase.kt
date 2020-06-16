package com.example.kotlinrepositories.home.domain.useCase

import com.example.kotlinrepositories.home.domain.entity.HomeEntity
import com.example.kotlinrepositories.home.domain.repository.HomeRepository
import com.example.kotlinrepositories.home.domain.repository.HomeRepositoryImpl
import io.reactivex.rxjava3.core.Observable

interface HomeUseCase {
fun getKotlinRepositories(page: Int): Observable<List<HomeEntity>>
}

class HomeUseCaseImpl(private val repository: HomeRepository): HomeUseCase {
    override fun getKotlinRepositories(page: Int): Observable<List<HomeEntity>> {
         return repository.getKotlinRepositoriesFromApi(page)
    }
}

