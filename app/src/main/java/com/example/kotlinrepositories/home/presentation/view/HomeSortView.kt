package com.example.kotlinrepositories.home.presentation.view

import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import com.example.kotlinrepositories.home.data.remote.SortType
import com.example.kotlinrepositories.home.presentation.viewModel.HomeViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_sort_layout.view.*


interface IHomeSort {
    fun updateVisibility()
}

class HomeSortView(layoutInflated: View, arrowView: View, private val vm: HomeViewModel): IHomeSort {

    private var isFilterOpen = false
    private var view: View = layoutInflated
    private var arrowView: View = arrowView

    init {
        Logger.i("initializing SortView hidden")
        view.visibility =  View.GONE
        this.configureRadioGroupColor()
        this.radioButtonChangeListener()
    }

    override fun updateVisibility() {
        this.isFilterOpen = !isFilterOpen
        if (isFilterOpen) this.show() else this.fade()
        this.arrowRotate()
    }

    private fun show() {
        Logger.i("show sortView")
        this.setUpdateVisibilityByAnimation(0.4f, 1f, 1000)
    }

    private fun fade() {
        Logger.i("hidden sortView")
        this.setUpdateVisibilityByAnimation(1f, 0f, 700)
    }

    private fun setUpdateVisibilityByAnimation(fromAlpha: Float, toAlpha: Float, duration: Long) {
        view.apply {
            alpha = fromAlpha
            animate()
                .alpha(toAlpha)
                .setDuration(duration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        if (isFilterOpen) {
                            visibility =  View.VISIBLE
                        }
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        if (!isFilterOpen) {
                            visibility =  View.GONE
                        }
                    }
                })
        }
    }

    private fun configureRadioGroupColor() {
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(R.attr.state_enabled)
            ), intArrayOf(
                Color.BLACK
            )
        )
        view.rg_home_sort.backgroundTintList = colorStateList
        view.rb_home_sort_forks.buttonTintList = colorStateList
        view.rb_home_sort_stars.buttonTintList = colorStateList
    }

    private fun radioButtonChangeListener() {
        view.rg_home_sort.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == view.rb_home_sort_stars.id) {
                Logger.i("sort by star selected")
                this.vm.fetchKotlinRepositoriesByPageAndSort(1, SortType.STAR)
            } else {
                Logger.i("sort by fork selected")
                this.vm.fetchKotlinRepositoriesByPageAndSort(1, SortType.FORK)
            }
            this.updateVisibility()
        }
    }

    private fun arrowRotate() {
     this.arrowView.animate().rotationBy(180f).start()
    }
}