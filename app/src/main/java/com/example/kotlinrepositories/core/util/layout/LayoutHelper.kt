package com.example.kotlinrepositories.core.util.layout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

enum class ColorEnum(val rawValue: Int) {
    BLACK(Color.BLACK)
}

object LayoutHelper {
    fun getBottomLineSetup(bottomLineColor: ColorEnum): LayerDrawable {
        val bottomColor = ColorDrawable(bottomLineColor.rawValue)
        val colorDefault = ColorDrawable(Color.WHITE)
        val layers = arrayOf<Drawable>(bottomColor, colorDefault)
        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 0, 0, 0, 0)
        layerDrawable.setLayerInset(1, 0, 0, 0, 5)
        return layerDrawable
    }
}