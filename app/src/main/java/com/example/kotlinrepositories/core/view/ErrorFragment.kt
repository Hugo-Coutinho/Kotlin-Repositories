package com.example.kotlinrepositories.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinrepositories.databinding.FragmentHomeErrorBinding

class ErrorFragment(private val message: String): Fragment() {

    private var binding: FragmentHomeErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeErrorBinding.inflate(layoutInflater)
        this.binding?.tvErrorMessage?.text = message
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }
}