package com.example.kotlinrepositories.pullRequestPage.domain.entity

import android.graphics.Color
import com.example.kotlinrepositories.pullRequestPage.data.model.PullElement

typealias PullEntity = ArrayList<PullEntityElement>

open class PullEntityElement(
    val userName: String,
    val pullName: String,
    val pullDescription: String,
    val avatar: String,
    val pullState: PullStateEnum
) {

    enum class PullStateEnum {
        OPENED, CLOSED
    }

    companion object {
        fun toEntity(model: PullElement): PullEntityElement {
            return PullEntityElement(model.user.login, model.title, model.description, model.user.userImageUrl, setPullState(model.state))
        }

        private fun setPullState(state: String): PullStateEnum {
            return if (state.toLowerCase().equals("open")) PullStateEnum.OPENED else PullStateEnum.CLOSED
        }
    }
}
