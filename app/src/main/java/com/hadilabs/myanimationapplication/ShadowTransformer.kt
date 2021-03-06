package com.hadilabs.myanimationapplication

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.view.View


class ShadowTransformer(private val mViewPager: ViewPager, private val mAdapter: CardAdapter) : ViewPager.OnPageChangeListener, ViewPager.PageTransformer {
    private var mLastOffset: Float = 0.toFloat()
    private var mScalingEnabled: Boolean = false

    init {
        mViewPager.addOnPageChangeListener(this)
    }

    fun enableScaling(enable: Boolean) {
        if (mScalingEnabled && !enable) {
            // shrink main card
            val currentTitleCard = mAdapter.getTitleCardViewAt( mViewPager.currentItem )
            val currentCard = mAdapter.getCardViewAt(mViewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1f)
                currentCard.animate().scaleX(1f)
            }
            if (currentTitleCard != null) {
                currentTitleCard.animate().scaleY(1f)
                currentTitleCard.animate().scaleX(1f)
            }
        } else if (!mScalingEnabled && enable) {
            // grow main card
            val currentTitleCard = mAdapter.getTitleCardViewAt( mViewPager.currentItem )
            val currentCard = mAdapter.getCardViewAt(mViewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f)
                currentCard.animate().scaleX(1.1f)
            }
            if (currentTitleCard != null) {
                currentTitleCard.animate().scaleY(1.1f)
                currentTitleCard.animate().scaleX(1.1f)
            }
        }

        mScalingEnabled = enable
    }

    override fun transformPage(page: View, position: Float) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val realCurrentPosition: Int
        val nextPosition: Int
        val baseElevation = mAdapter.baseElevation
        val realOffset: Float
        val goingLeft = mLastOffset > positionOffset

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1 || realCurrentPosition > mAdapter.getCount() - 1) {
            return
        }


        val currentTitleCard = mAdapter.getTitleCardViewAt( realCurrentPosition )
        val currentCard = mAdapter.getCardViewAt(realCurrentPosition)

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
            }
            ViewCompat.setElevation( currentCard, baseElevation + (baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)) )

        }

        if (currentTitleCard != null) {
            if (mScalingEnabled) {
                currentTitleCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentTitleCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
            }
            currentTitleCard.cardElevation = baseElevation + (baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset))
        }


        val nextTitleCard = mAdapter.getTitleCardViewAt( nextPosition )
        val nextCard = mAdapter.getCardViewAt(nextPosition)

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.scaleX = (1 + 0.1 * realOffset).toFloat()
                nextCard.scaleY = (1 + 0.1 * realOffset).toFloat()
            }
            ViewCompat.setElevation(nextCard , baseElevation + (baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * realOffset))

        }

        if (nextTitleCard != null) {
            if (mScalingEnabled) {
                nextTitleCard.scaleX = (1 + 0.1 * realOffset).toFloat()
                nextTitleCard.scaleY = (1 + 0.1 * realOffset).toFloat()
            }
            nextTitleCard.cardElevation = baseElevation + (baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * realOffset)
        }

        mLastOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}