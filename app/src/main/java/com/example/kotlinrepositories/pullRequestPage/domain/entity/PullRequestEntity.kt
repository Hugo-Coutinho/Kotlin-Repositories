package com.example.kotlinrepositories.pullRequestPage.domain.entity

import com.example.kotlinrepositories.pullRequestPage.data.model.PullElement

typealias PullEntity = ArrayList<PullEntityElement>

open class PullEntityElement(
    val userName: String,
    val pullName: String,
    val pullDescription: String,
    val avatar: String
) {
    companion object {
        fun toEntity(model: PullElement): PullEntityElement {
            return PullEntityElement(model.user.login, model.title, model.description, model.user.userImageUrl)
        }
    }
}
