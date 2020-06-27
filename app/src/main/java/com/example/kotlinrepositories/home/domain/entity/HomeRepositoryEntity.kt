package com.example.kotlinrepositories.home.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class HomeRepositoryEntity(
    val repositoryName: String,
    val userName: String,
    val repoDescription: String? = null,
    val avatar: String? = null,
    val repositoryPageLink: String? = null,
    val repositoryStarsTotal: Long? = null,
    val forksTotal: Long? = null
): Parcelable
