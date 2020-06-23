package com.example.kotlinrepositories.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrepositories.databinding.FragmentHomeListingRepositoriesBinding
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.presentation.recyclerView.adapter.RecyclerAdapter


class HomeListingRepositoriesFragment(private val items: List<HomeRepositoryEntity>): Fragment() {

    private var binding: FragmentHomeListingRepositoriesBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeListingRepositoriesBinding.inflate(layoutInflater)
        this.prepareRecyclerView()
        return this.getViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun prepareRecyclerView() {
        linearLayoutManager = LinearLayoutManager(activity)
        this.binding?.recyclerView?.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(items)
        this.binding?.recyclerView?.adapter = adapter
    }

    private fun getViewCreated(): LinearLayout? {
        return binding?.root
    }

}