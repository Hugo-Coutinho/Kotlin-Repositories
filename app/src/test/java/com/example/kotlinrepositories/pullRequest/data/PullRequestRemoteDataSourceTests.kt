package com.example.kotlinrepositories.pullRequest.data

import com.example.kotlinrepositories.core.di.provideRetrofit
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestRemoteDataSourceTests {

    @Mock
    private lateinit var client: IGithubPullsApi
    private lateinit var dataSource: PullRequestRemoteDataSource

    @Before
    fun setUp() {
        this.client = Mockito.mock(provideRetrofit().create(IGithubPullsApi::class.java)::class.java)
        this.dataSource = PullRequestRemoteDataSourceImpl(this.client)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getPullRequests_ShouldAssertOneItemCount() {

        // GIVEN
        var resultMockItemscount: Int = 0
        val expectedResult = MockPullRequestRemoteDataSource.didClientResponse()
        Mockito.`when`(this.client.findPullRequest("Rogerin", "KotlinRepository")).thenReturn(expectedResult)

        // WHEN
        this.dataSource.getPullRequest("Rogerin", "KotlinRepository")
            .subscribe({
                resultMockItemscount = it.count()
            }, {
                Assert.fail()
            })

        // THEN
        Assert.assertEquals(1, resultMockItemscount)
    }

}
