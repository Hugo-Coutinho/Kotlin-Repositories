package com.example.kotlinrepositories.home.data.model

data class Item(val items: List<KotlinRepositoriesModel>)

data class KotlinRepositoriesModel (
    val name: String,
    val private: Boolean,
    val owner: Owner,
    val description: String,
    val html_url: String,
    val stargazers_count: Long,
    val forks_count: Long
//): HomeEntity(name, private, owner.login, description, html_url, stargazers_count, forks_count)
)

data class Owner (
    val login: String,
    val avatar_url: String
)
