package com.example.kotlinrepositories.home.data

import com.example.kotlinrepositories.core.di.provideRetrofit
import com.example.kotlinrepositories.pullRequest.data.MockPullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.data.remote.IGithubPullsApi
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSourceImpl
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
    private lateinit var client: IGithubPullsApi
    private lateinit var dataSource: PullRequestRemoteDataSource

    @Before
    fun setUp() {
        this.client =  Mockito.mock(provideRetrofit().create(IGithubPullsApi::class.java)::class.java)
        this.dataSource = PullRequestRemoteDataSourceImpl(this.client)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getKotlinRepositories_ShouldAssertOneItemCount() {

        // GIVEN
        var resultMockItemscount: Int = 0
        val expectedResult = MockPullRequestRemoteDataSource.didClientResponse()
        `when`(this.client.findPullRequest("", "")).thenReturn(expectedResult)

        // WHEN
        this.dataSource.getPullRequest("", "")
            .subscribe({
                resultMockItemscount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultMockItemscount)
    }
}