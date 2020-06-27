package com.example.kotlinrepositories.pullRequestPage.presentation.recyclerView.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.extension.inflate
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.presentation.recyclerView.viewHolder.PullRequestHolder

class PRRecyclerAdapter(private val items: PullEntity): RecyclerView.Adapter<PullRequestHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestHolder {
        val inflatedView = parent.inflate(R.layout.pull_request_item, false)
        return PullRequestHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PullRequestHolder, position: Int) = holder.bindView(items[position])
}