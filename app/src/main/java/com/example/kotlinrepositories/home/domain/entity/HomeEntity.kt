package com.example.kotlinrepositories.home.domain.entity

open class HomeEntity(
    val repositoryName: String? = null,
    val isPrivate: Boolean? = null,
    val userName: String? = null,
    val repoDescription: String? = null,
    val repositoryPageLink: String? = null,
    val repositoryStarsTotal: Long? = null,
    val forksTotal: Long? = null
)
