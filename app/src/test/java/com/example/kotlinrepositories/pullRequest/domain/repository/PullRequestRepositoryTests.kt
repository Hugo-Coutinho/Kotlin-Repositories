package com.example.kotlinrepositories.pullRequest.domain.repository

import com.example.kotlinrepositories.pullRequest.data.MockPullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepository
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepositoryImpl
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestRepositoryTests {

    @Mock
    private lateinit var Repository: PullRequestRepository
    @Mock
    private lateinit var dataSource: PullRequestRemoteDataSource

    @Before
    fun setUp() {
        this.dataSource =  Mockito.mock(MockPullRequestRemoteDataSource.getPullRequestRemoteDataSource()::class.java)
        this.Repository = PullRequestRepositoryImpl(this.dataSource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getPullsFromApi_ShouldAssertOneItemCount() {

        // GIVEN
        var resultEntityCount: Int = 0
        val mockItems = MockPullRequestRemoteDataSource.getMockPulls()
        Mockito.`when`(this.dataSource.getPullRequest("Rogerin", "KotlinRepository")).thenReturn(mockItems)

        // WHEN
        this.Repository.getPullsFromApi("Rogerin", "KotlinRepository")
            .subscribe({
                resultEntityCount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultEntityCount)
    }
}