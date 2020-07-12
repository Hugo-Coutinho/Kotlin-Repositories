package com.example.kotlinrepositories.home.domain.repository

import com.example.kotlinrepositories.home.data.MockHomeRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.KotlinRepositoriesRemoteDataSource
import com.example.kotlinrepositories.home.data.remote.SortType
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
class HomeRepositoryTests {

    @Mock
    private lateinit var homeRepository: HomeRepository
    @Mock
    private lateinit var dataSource: KotlinRepositoriesRemoteDataSource

    @Before
    fun setUp() {
        this.dataSource =  Mockito.mock(MockHomeRemoteDataSource.getKotlinRepositoriesRemoteDataSource()::class.java)
        this.homeRepository = HomeRepositoryImpl(this.dataSource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getKotlinRepositoriesFromApi_ShouldAssertOneItemCount() {

        // GIVEN
        var resultEntityCount: Int = 0
        val mockItems = MockHomeRemoteDataSource.getMockKotlinRepositories()
        `when`(this.dataSource.getKotlinRepositories(1, SortType.STAR)).thenReturn(mockItems)

        // WHEN
        this.homeRepository.getKotlinRepositoriesFromApi(1, SortType.STAR)
            .subscribe({
                resultEntityCount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultEntityCount)
    }
}