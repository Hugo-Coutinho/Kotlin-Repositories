package com.example.kotlinrepositories.pullRequestPage.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrepositories.databinding.FragmentPullRequestListBinding
import com.example.kotlinrepositories.pullRequestPage.domain.entity.PullEntity
import com.example.kotlinrepositories.pullRequestPage.presentation.recyclerView.adapter.PRRecyclerAdapter
import com.example.kotlinrepositories.pullRequestPage.presentation.viewModel.PullRequestViewModel

class PullRequestListFragment(private val vm: PullRequestViewModel, private val items: PullEntity): Fragment() {

    private var binding: FragmentPullRequestListBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PRRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentPullRequestListBinding.inflate(layoutInflater)
        this.prepareRecyclerView()
        return this.getViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun prepareRecyclerView() {
        linearLayoutManager = LinearLayoutManager(activity)
        this.binding?.prRecyclerView?.layoutManager = linearLayoutManager
        adapter = PRRecyclerAdapter(items)
        this.binding?.prRecyclerView?.adapter = adapter
    }

    private fun getViewCreated(): LinearLayout? {
      return binding?.root
    }
}