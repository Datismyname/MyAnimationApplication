package com.hadilabs.myanimationapplication


import android.support.v7.widget.CardView

interface CardAdapter {

    val baseElevation: Float

    fun getCount(): Int

    fun getCardViewAt(position: Int): CardView

    companion object {

        val MAX_ELEVATION_FACTOR = 8
    }
}