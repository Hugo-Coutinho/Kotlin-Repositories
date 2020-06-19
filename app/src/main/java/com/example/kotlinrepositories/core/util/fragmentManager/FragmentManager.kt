package com.example.kotlinrepositories.core.util.fragmentManager

import androidx.fragment.app.FragmentActivity

interface FragmentNavigationManager {
    fun add(from: FragmentActivity, resourceId: Int, to: androidx.fragment.app.Fragment)
    fun replace(from: FragmentActivity, resourceId: Int, to: androidx.fragment.app.Fragment)
}

class FragmentNavigationManagerImpl: FragmentNavigationManager {
        override fun add(from: FragmentActivity, resourceId: Int, to: androidx.fragment.app.Fragment) {
            from
                .supportFragmentManager
                .beginTransaction()
                .add(resourceId, to)
                .addToBackStack(null)
                .commit()
        }

        override fun replace(from: FragmentActivity, resourceId: Int, to: androidx.fragment.app.Fragment) {
            from
                .supportFragmentManager
                .beginTransaction()
                .replace(resourceId, to)
                .addToBackStack(null)
                .commit()
        }
}