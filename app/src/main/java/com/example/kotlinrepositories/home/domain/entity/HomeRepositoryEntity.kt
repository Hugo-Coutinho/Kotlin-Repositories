package com.example.kotlinrepositories.home.domain.entity

import android.os.Parcelable
import com.example.kotlinrepositories.home.data.model.KotlinRepositoriesElement
import kotlinx.android.parcel.Parcelize

typealias HomeRepositoryEntity = ArrayList<HomeRepositoryEntityElement>

@Parcelize
open class HomeRepositoryEntityElement(
    val repositoryName: String,
    val userName: String,
    val repoDescription: String? = null,
    val avatar: String? = null,
    val isPrivateRepository: Boolean = false,
    val repositoryPageLink: String? = null,
    val repositoryStarsTotal: Long? = null,
    val forksTotal: Long? = null
): Parcelable {
    fun isPageLinkNotNull(): Boolean {
        return repositoryPageLink != null
    }

    companion object {
        fun toEntity(model: KotlinRepositoriesElement): HomeRepositoryEntityElement {
            return HomeRepositoryEntityElement(model.name, model.owner.login, model.description, model.owner.userImageUrl, model.isPrivate, model.repositoryPage, model.starCount, model.forkCount)
        }
    }
}
