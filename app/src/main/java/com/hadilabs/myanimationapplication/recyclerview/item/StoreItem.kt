package com.hadilabs.myanimationapplication.recyclerview.item

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.hadilabs.myanimationapplication.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_store.*


    var isExpanded = false


class StoreItem(val title: String, val body:String): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView_title.text = title
        viewHolder.textView_body.text = body


        viewHolder.relativeLayout_cardView_content.setOnClickListener {

            val imageView_profile_pic_params = viewHolder.imageView_profile_pic.layoutParams as RelativeLayout.LayoutParams
            val textView_title_params = viewHolder.textView_title.layoutParams as RelativeLayout.LayoutParams


            viewHolder.relativeLayout_cardView_content.animate()
                    .setDuration(300)
                    .translationY(0f)
                    .setListener( MyAnimation(viewHolder.imageView_profile_pic, imageView_profile_pic_params, viewHolder.textView_title, textView_title_params, viewHolder.textView_body, viewHolder) )
                    .interpolator = FastOutSlowInInterpolator()


        }


    }

    override fun getLayout() = R.layout.item_store


}


class MyAnimation(val imageView: ImageView, val imageParams: RelativeLayout.LayoutParams, val textView: TextView, val textParams: RelativeLayout.LayoutParams, val bodyText: TextView, val viewHolder:ViewHolder) : AnimatorListenerAdapter() {

    override fun onAnimationStart(animation: Animator?) {
        super.onAnimationStart(animation)


        if ( !isExpanded ) {
            TransitionManager.beginDelayedTransition( viewHolder.relativeLayout_cardView_content )
            imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_START, 0)

            textParams.addRule(RelativeLayout.BELOW, R.id.imageView_profile_pic)
            textParams.addRule(RelativeLayout.END_OF, 0)

            imageView.layoutParams = imageParams
            textView.layoutParams = textParams

            bodyText.visibility = View.VISIBLE

            isExpanded = true

        }else{
            TransitionManager.beginDelayedTransition( viewHolder.relativeLayout_cardView_content )

            imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL,0)
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_START)

            textParams.addRule(RelativeLayout.BELOW, 0)
            textParams.addRule(RelativeLayout.END_OF, R.id.imageView_profile_pic)

            imageView.layoutParams = imageParams
            textView.layoutParams = textParams



            isExpanded = false


        }

    }

    override fun onAnimationEnd(animation: Animator?) {
        super.onAnimationEnd(animation)
        TransitionManager.beginDelayedTransition( viewHolder.relativeLayout_cardView_content )

        if ( !isExpanded )
        bodyText.visibility = View.GONE
    }



}