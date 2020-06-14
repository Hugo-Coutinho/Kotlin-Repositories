package com.example.kotlinrepositories.home.data

import com.example.kotlinrepositories.core.di.provideRetrofit
import com.example.kotlinrepositories.home.data.model.Item
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesModel
import com.example.kotlinrepositories.home.data.model.Owner
import com.example.kotlinrepositories.home.data.remote.IGithubApi
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSourceImpl
import io.reactivex.rxjava3.core.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeRemoteDataSourceTests {

    @Mock
    private lateinit var client: IGithubApi
    private lateinit var dataSource: KotlinRepositoriesRemoteDataSource

    @Before
    fun setUp() {
        this.client =  Mockito.mock(provideRetrofit().create(IGithubApi::class.java)::class.java)
        this.dataSource = KotlinRepositoriesRemoteDataSourceImpl(this.client)
    }

    @After
    fun tearDown() {

    }

    private fun mockObject(): KotlinRepositoriesModel {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        val item = Item("architecture-samples", false, owner, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.","https://github.com/google/flexbox-layout",36642,10175)
        return KotlinRepositoriesModel(items = listOf(item))
    }

    @Test
    fun getKotlinRepositories_shouldAssertRepositoryModel() {
        // GIVEN
        var result: Int = 0
        val expectedResult = Observable.just(mockObject())
        `when`(this.client.findKotlinRepositories("language:kotlin", 1)).thenReturn(expectedResult)

        // WHEN
        this.dataSource.getKotlinRepositories(1)
            .subscribe({
                result = it.items.count()
            }, {
                Assert.fail()
            })
                // THEN
                Assert.assertEquals(1, result)
    }
}