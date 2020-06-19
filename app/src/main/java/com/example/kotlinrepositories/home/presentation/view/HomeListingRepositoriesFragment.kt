package com.example.kotlinrepositories.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinrepositories.R
import com.example.kotlinrepositories.core.util.fragmentManager.FragmentNavigationManager
import com.example.kotlinrepositories.databinding.FragmentHomeListingRepositoriesBinding
import org.koin.android.ext.android.inject


class HomeListingRepositoriesFragment : Fragment() {

    private val fragmentManager: FragmentNavigationManager by inject()
    private var binding: FragmentHomeListingRepositoriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeListingRepositoriesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding?.successFragmentTyping?.text = "binding works well inside of fragment"
        this.binding?.successFragmentClick?.setOnClickListener {
            activity?.let {
                fragmentManager.replace(it, R.id.home_fragment_container, HomeErrorFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }
}