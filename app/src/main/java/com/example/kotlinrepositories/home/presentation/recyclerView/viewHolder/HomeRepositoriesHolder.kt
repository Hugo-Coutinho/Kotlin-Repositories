package com.example.kotlinrepositories.home.presentation.recyclerView.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.orhanobut.logger.Logger
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_repository_item.view.*

//1
class HomeRepositoriesHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    init {
        v.setOnClickListener(this)
    }

    override fun onClick(v: View) {
    Logger.d("item clicked")
    }

    fun bindRepository(item: HomeRepositoryEntity) {
        this.downloadingUserAvatar(item.avatar)
        view.tv_user_name.text = item.userName
        view.tv_repository_name.text = item.repositoryName
        view.tv_repository_description.text = item.repoDescription
        view.tv_fork_count.text = "${item.forksTotal}"
        view.tv_star_count.text = "${item.repositoryStarsTotal}"
    }

    private fun downloadingUserAvatar(imageUrl: String?) {
        Picasso.with(view.context)
            .load(imageUrl)
            .into(view.iv_user, object : Callback {
                override fun onSuccess() {

                }

                override fun onError() {
                    Logger.wtf("Something went wrong with image downloading $imageUrl")
                    view.iv_user.setImageResource(R.drawable.user_default)
                }
            })
    }
}