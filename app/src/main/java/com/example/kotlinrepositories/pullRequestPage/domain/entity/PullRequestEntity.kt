package com.example.kotlinrepositories.pullRequestPage.domain.entity

typealias PullEntity = ArrayList<PullEntityElement>

open class PullEntityElement(
    val userName: String,
    val pullName: String,
    val pullDescription: String,
    val avatar: String
)
