package com.hadilabs.myanimationapplication


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView

interface CardAdapter {

    val baseElevation: Float

    fun getCount(): Int


    fun getTitleCardViewAt(position: Int): CardView

    fun getCardViewAt(position: Int): RecyclerView

    companion object {

        val MAX_ELEVATION_FACTOR = 8
    }
}