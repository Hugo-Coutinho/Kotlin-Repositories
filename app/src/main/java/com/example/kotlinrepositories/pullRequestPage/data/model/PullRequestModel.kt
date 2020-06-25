package com.example.kotlinrepositories.pullRequestPage.data.model

typealias PullModel = ArrayList<PullElement>

data class PullElement (
    val title: String,
    val user: User,
    val body: String
)

data class User (
    val login: String,
    val avatarURL: String
)
