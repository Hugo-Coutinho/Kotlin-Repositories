package com.example.kotlinrepositories.home.data.model

import com.google.gson.annotations.SerializedName

typealias KotlinRepositoriesModel = ArrayList<KotlinRepositoriesElement>

data class Item(val items: ArrayList<KotlinRepositoriesElement>)

data class KotlinRepositoriesElement (
    @SerializedName("name")
    val name: String,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String,
    @SerializedName("html_url")
    val repositoryPage: String,
    @SerializedName("stargazers_count")
    val starCount: Long,
    @SerializedName("forks_count")
    val forkCount: Long
)

data class Owner (
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val userImageUrl: String
)
