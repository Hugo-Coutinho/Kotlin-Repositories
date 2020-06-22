package com.example.kotlinrepositories.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.databinding.FragmentHomeListingRepositoriesBinding
import com.example.kotlinrepositories.home.data.model.Owner
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.presentation.recyclerView.adapter.RecyclerAdapter
import org.koin.android.ext.android.inject


class HomeListingRepositoriesFragment : Fragment() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private var binding: FragmentHomeListingRepositoriesBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.prepareRecyclerView()
        return this.getViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun mockEntity(): HomeRepositoryEntity {
        val owner = Owner("android","https://avatars3.githubusercontent.com/u/32689599?v=4")
        return HomeRepositoryEntity("architecture-samples",false, owner.login, "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.",owner.avatar_url,"https://github.com/google/flexbox-layout", 36642,10175)
    }

    private fun prepareRecyclerView() {
        this.binding = FragmentHomeListingRepositoriesBinding.inflate(layoutInflater)
        linearLayoutManager = LinearLayoutManager(activity)
        this.binding?.recyclerView?.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(listOf(mockEntity(), mockEntity(), mockEntity(), mockEntity()))
        this.binding?.recyclerView?.adapter = adapter
    }

    private fun getViewCreated(): LinearLayout? {
        return binding?.root
    }

}