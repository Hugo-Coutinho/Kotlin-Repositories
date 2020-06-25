package com.example.kotlinrepositories.pullRequest.data

import com.example.kotlinrepositories.core.di.provideRetrofit
import com.example.kotlinrepositories.pullRequestPage.data.model.PullElement
import com.example.kotlinrepositories.pullRequestPage.data.model.PullModel
import com.example.kotlinrepositories.pullRequestPage.data.model.User
import com.example.kotlinrepositories.pullRequestPage.data.remote.IGithubPullsApi
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSource
import com.example.kotlinrepositories.pullRequestPage.data.remote.PullRequestRemoteDataSourceImpl
import io.reactivex.rxjava3.core.Observable
import org.mockito.Mock
import org.mockito.Mockito

object MockPullRequestRemoteDataSource {

    @Mock
    private lateinit var client: IGithubPullsApi

    fun getPullRequestRemoteDataSource(): PullRequestRemoteDataSource {
        this.client =  Mockito.mock(provideRetrofit().create(IGithubPullsApi::class.java)::class.java)
        return PullRequestRemoteDataSourceImpl(this.client)
    }

    fun getMockPulls(): Observable<PullModel> {
        return Observable.just(this.mockModel())
    }

    fun didClientResponse(): Observable<PullModel> {
        return Observable.just(this.mockModel())
    }

    private fun mockModel(): PullModel {
        val model = PullModel()
        val user = User("G00fY2", "https://avatars1.githubusercontent.com/u/1990806?v=4")
        val element = PullElement("Allow referencing custom fonts by string or as a font resource reference", user, "Currently the attributes for custom fonts are limited to font resource references only.\r\n```xml\r\n<item name=\"md_font_title\">@font/your_font</item>\r\n<item name=\"md_font_body\">@font/your_font</item>\r\n<item name=\"md_font_button\">@font/your_font</item>\r\n```\r\nIt is not possible to reference system fonts this way (e.g. the Roboto font family).\r\n\r\nThis PR is inspired by the TextView's [`android:fontFamily`](https://developer.android.com/reference/android/widget/TextView#attr_android:fontFamily) attribute and uses the same logic to resolve the typeface.\r\n\r\nThis PR will not interference with the current behavior, but it allows to additionally reference fonts by string:\r\n```xml\r\n<item name=\"md_font_title\">sans-serif-black</item>\r\n<item name=\"md_font_body\">sans-serif-light</item>\r\n<item name=\"md_font_button\">@font/your_font</item>\r\n```")

        model.add(element)
        return model
    }
}