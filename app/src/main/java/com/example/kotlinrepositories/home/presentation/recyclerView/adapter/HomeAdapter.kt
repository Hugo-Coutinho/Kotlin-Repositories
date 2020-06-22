package com.example.kotlinrepositories.home.presentation.recyclerView.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.extension.inflate
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.presentation.recyclerView.viewHolder.HomeRepositoriesHolder

class RecyclerAdapter(private val items: List<HomeRepositoryEntity>): RecyclerView.Adapter<HomeRepositoriesHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRepositoriesHolder {
        val inflatedView = parent.inflate(R.layout.home_repository_item, false)
        return HomeRepositoriesHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeRepositoriesHolder, position: Int) = holder.bindRepository(items[position])
}