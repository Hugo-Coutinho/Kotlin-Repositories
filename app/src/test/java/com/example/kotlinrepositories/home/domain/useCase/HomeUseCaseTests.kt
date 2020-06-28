package com.example.kotlinrepositories.home.domain.useCase

import com.example.kotlinrepositories.home.domain.repository.HomeRepository
import com.example.kotlinrepositories.home.domain.repository.MockHomeRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeUseCaseTests {

    @Mock
    private lateinit var useCase: HomeUseCase
    @Mock
    private lateinit var repository: HomeRepository

    @Before
    fun setUp() {
        this.repository =  Mockito.mock(MockHomeRepository.getMockRepository()::class.java)
        this.useCase = HomeUseCaseImpl(this.repository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getKotlinRepositories_ShouldAssertEntity() {

        // GIVEN
        var resultEntityCount = 0
        val mockItems = MockHomeRepository.getMockKotlinRepositories()
        Mockito.`when`(this.repository.getKotlinRepositoriesFromApi(1)).thenReturn(mockItems)

        // WHEN
        this.useCase.getKotlinRepositories(1)
            .subscribe({
                resultEntityCount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultEntityCount)
    }
}
