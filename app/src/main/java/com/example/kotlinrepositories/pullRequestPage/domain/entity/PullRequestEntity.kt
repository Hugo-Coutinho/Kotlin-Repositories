package com.example.kotlinrepositories.pullRequestPage.domain.entity

typealias PullEntity = ArrayList<PullEntityElement>

open class PullEntityElement(
    val userName: String? = null,
    val repositoryName: String? = null,
    val repositoryDescription: String? = null,
    val avatar: String? = null
)
