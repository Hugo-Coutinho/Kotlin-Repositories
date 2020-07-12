package com.example.kotlinrepositories.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepositories.databinding.FragmentHomeListingRepositoriesBinding
import com.example.kotlinrepositories.home.domain.entity.HomeRepositoryEntity
import com.example.kotlinrepositories.home.presentation.recyclerView.adapter.RecyclerAdapter
import com.example.kotlinrepositories.home.presentation.viewModel.HomeViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_home_listing_repositories.*


class HomeListingRepositoriesFragment(private val vm: HomeViewModel, private var items: HomeRepositoryEntity): Fragment() {

    private var binding: FragmentHomeListingRepositoriesBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private var page = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeListingRepositoriesBinding.inflate(layoutInflater)
        this.prepareRecyclerView()
        this.observingItems()
        this.listeningScrollFromRecyclerView()
        return this.getViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun listeningScrollFromRecyclerView() {
        this.binding?.recyclerView?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val pastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if(!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) == total) {
                        Logger.d("we have visible items: $visibleItemCount and pastaVisibleItems: $pastVisibleItem equal to total: $total")
                        getPageWithEndlessScroll()
                    }
                }
            }
        })

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

    private fun getPageWithEndlessScroll() {
        page++
        this.setVisibleLoading()
        Logger.w("making the request again calling page $page")
        fetchRepositoriesByNextPage(page)
    }

    private fun fetchRepositoriesByNextPage(page: Int) {
        this.vm.fetchRepositoriesNextPageInfiniteScroll(page)
    }

    private fun observingItems() {
        this.vm.viewModelData.vmItems.observe(viewLifecycleOwner, Observer {
            this.items = it
            Logger.i("new items updated, now with ${this.items.count()} items")
            if (::adapter.isInitialized) {
                Logger.i("adapter initializaded, updating the ui")
                adapter.notifyDataSetChanged()
            }
            this.setHiddenLoading()
        })
    }

    private fun setVisibleLoading() {
        isLoading = true
        home_progressBar.visibility = View.VISIBLE
    }

    private fun setHiddenLoading() {
        isLoading = false
        home_progressBar.visibility = View.INVISIBLE
    }
}