package com.example.kotlinrepositories.core.di

import com.example.kotlinrepositories.core.util.constant.Constant
import com.example.kotlinrepositories.home.data.remote.IGithubApi
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSourceImpl
import com.example.kotlinrepositories.home.domain.repository.HomeRepository
import com.example.kotlinrepositories.home.domain.repository.HomeRepositoryImpl
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCase
import com.example.kotlinrepositories.home.domain.useCase.HomeUseCaseImpl
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val gson = GsonBuilder().setLenient().create()

    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .baseUrl(Constant.baseUrl)
        .build()
}

val clientModule = module {
    factory<IGithubApi> { provideRetrofit().create(IGithubApi::class.java) }
}

val dataModule = module {
    factory<KotlinRepositoriesRemoteDataSource> { KotlinRepositoriesRemoteDataSourceImpl(client = get()) }
}

val repositoryModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(remoteDataSource = get()) }
}

val useCaseModule = module {
    factory<HomeUseCase> { HomeUseCaseImpl(repository = get()) }
}



