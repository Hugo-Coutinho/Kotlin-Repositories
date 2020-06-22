package com.example.kotlinrepositories.home.presentation.recyclerView.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.orhanobut.logger.Logger
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_repository_item.view.*

//1
class HomeRepositoriesHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    init {
        v.setOnClickListener(this)
    }

    override fun onClick(v: View) {
    Logger.d("item clicked ")
    }

    fun bindRepository(item: HomeRepositoryEntity) {
        Picasso.with(view.context).load(item.avatar).into(view.iv_user)
        view.tv_user_name.text = item.userName
        view.tv_repository_name.text = item.repositoryName
        view.tv_repository_description.text = item.repoDescription
        view.tv_fork_count.text = "${item.forksTotal}"
        view.tv_star_count.text = "${item.repositoryStarsTotal}"
    }
}