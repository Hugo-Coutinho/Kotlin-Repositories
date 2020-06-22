package com.example.kotlinrepositories.home.domain.entity

open class HomeRepositoryEntity(
    val repositoryName: String? = null,
    val isPrivate: Boolean? = null,
    val userName: String? = null,
    val repoDescription: String? = null,
    val avatar: String? = null,
    val repositoryPageLink: String? = null,
    val repositoryStarsTotal: Long? = null,
    val forksTotal: Long? = null
)
