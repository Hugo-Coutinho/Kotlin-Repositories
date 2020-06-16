package com.example.kotlinrepositories.home.data

import com.example.kotlinrepositories.core.di.provideRetrofit
import com.example.kotlinrepositories.home.data.remote.IGithubApi
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSourceImpl
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
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

    @Test
    fun getKotlinRepositories_shouldAssertRepositoryModel() {

        // GIVEN
        var resultMockItemscount: Int = 0
        val expectedResult = MockHomeRemoteDataSource.didClientResponse()
        `when`(this.client.findKotlinRepositories(1)).thenReturn(expectedResult)

        // WHEN
        this.dataSource.getKotlinRepositories(1)
            .subscribe({
                resultMockItemscount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultMockItemscount)
    }
}