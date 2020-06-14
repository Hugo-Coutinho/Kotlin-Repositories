package com.example.kotlinrepositories.home.data.model

data class KotlinRepositoriesModel (
    val items: List<Item>
)

data class Item (
    val name: String,
    val private: Boolean,
    val owner: Owner,
    val description: String,
    val htmlURL: String,
    val stargazersCount: Long,
    val forksCount: Long
)

data class Owner (
    val login: String,
    val avatarURL: String
)
