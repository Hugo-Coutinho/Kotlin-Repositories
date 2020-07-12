package com.example.kotlinrepositories.pullRequestPage.data.model

import com.google.gson.annotations.SerializedName

typealias PullModel = ArrayList<PullElement>

data class PullElement (
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("body")
    val description: String
)

data class User (
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val userImageUrl: String
)
