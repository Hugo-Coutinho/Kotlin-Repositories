package com.example.kotlinrepositories.pullRequest.domain.repository

import com.example.kotlinrepositories.pullRequest.data.MockPullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntityElement
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepository
import com.example.kotlinrepositories.pullRequestPage.domain.repository.PullRequestRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import org.mockito.Mock
import org.mockito.Mockito

object MockPullRequestRepository {

    @Mock
    private lateinit var remoteDataSource: PullRequestRemoteDataSource

    fun getMockRepository(): PullRequestRepository {
        this.remoteDataSource =  Mockito.mock(MockPullRequestRemoteDataSource.getPullRequestRemoteDataSource()::class.java)
        return PullRequestRepositoryImpl(this.remoteDataSource)
    }

    fun getMockKotlinRepositories(): Observable<PullEntity> {
        return Observable.just(this.mockEntity())
    }

    private fun mockEntity(): PullEntity {
        val entity = PullEntity()
        val element = PullEntityElement("G00fY2","Allow referencing custom fonts by string or as a font resource reference","Currently the attributes for custom fonts are limited to font resource references only.\r\n```xml\r\n<item name=\"md_font_title\">@font/your_font</item>\r\n<item name=\"md_font_body\">@font/your_font</item>\r\n<item name=\"md_font_button\">@font/your_font</item>\r\n```\r\nIt is not possible to reference system fonts this way (e.g. the Roboto font family).\r\n\r\nThis PR is inspired by the TextView's [`android:fontFamily`](https://developer.android.com/reference/android/widget/TextView#attr_android:fontFamily) attribute and uses the same logic to resolve the typeface.\r\n\r\nThis PR will not interference with the current behavior, but it allows to additionally reference fonts by string:\r\n```xml\r\n<item name=\"md_font_title\">sans-serif-black</item>\r\n<item name=\"md_font_body\">sans-serif-light</item>\r\n<item name=\"md_font_button\">@font/your_font</item>\r\n```","https://avatars1.githubusercontent.com/u/1990806?v=4",PullEntityElement.PullStateEnum.OPENED)
        entity.add(element)
        return entity
    }
}