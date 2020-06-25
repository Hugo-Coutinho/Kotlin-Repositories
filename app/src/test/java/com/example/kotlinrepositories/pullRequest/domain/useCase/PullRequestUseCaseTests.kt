package com.example.kotlinrepositories.pullRequest.domain.useCase

import com.example.kotlinrepositories.pullRequest.domain.repository.MockPullRequestRepository
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepository
import com.example.kotlinrepositories.pullRequestPage.domain.useCase.PullRequestUseCase
import com.example.kotlinrepositories.pullRequestPage.domain.useCase.PullRequestUseCaseImpl
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestUseCaseTests {

    @Mock
    private lateinit var useCase: PullRequestUseCase
    @Mock
    private lateinit var repository: PullRequestRepository

    @Before
    fun setUp() {
        this.repository =  Mockito.mock(MockPullRequestRepository.getMockRepository()::class.java)
        this.useCase = PullRequestUseCaseImpl(this.repository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getKotlinRepositories_ShouldAssertOneItemCount() {

        // GIVEN
        var resultEntityCount: Int = 0
        val mockItems = MockPullRequestRepository.getMockKotlinRepositories()
        Mockito.`when`(this.repository.getPullsFromApi("Rogerin", "KotlinRepository")).thenReturn(mockItems)

        // WHEN
        this.useCase.gePulls("Rogerin", "KotlinRepository")
            .subscribe({
                resultEntityCount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultEntityCount)
    }
}