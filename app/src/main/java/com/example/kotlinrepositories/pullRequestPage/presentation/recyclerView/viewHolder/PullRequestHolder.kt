package com.example.kotlinrepositories.pullRequestPage.presentation.recyclerView.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntityElement
import com.orhanobut.logger.Logger
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pull_request_item.view.*

class PullRequestHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        Logger.d("${v.tv_pull_name} was clicked")
    }

    fun bindView(item: PullEntityElement) {
        this.downloadingUserAvatar(item.avatar)
        view.tv_pull_name.text = item.pullName
        view.tv_pull_description.text = item.pullDescription
        view.pr_tv_user_name.text = item.userName
    }

    private fun downloadingUserAvatar(imageUrl: String?) {
        Picasso.with(view.context)
            .load(imageUrl)
            .into(view.pr_iv_user, object : Callback {
                override fun onSuccess() {

                }

                override fun onError() {
                    Logger.wtf("Something went wrong with image downloading $imageUrl")
                    view.pr_iv_user.setImageResource(R.drawable.user_default)
                }
            })
    }
}