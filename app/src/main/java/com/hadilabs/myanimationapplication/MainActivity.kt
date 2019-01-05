package com.hadilabs.myanimationapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


var isExpanded = false

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab.setOnClickListener {
            val intent = Intent(this,StoreActivity::class.java)
            startActivity(intent)
        }



        relativeLayout_cardView_content.setOnClickListener {

            TransitionManager.beginDelayedTransition( relativeLayout_cardView_content )

            val imageView_profile_pic_params = imageView_profile_pic.layoutParams as RelativeLayout.LayoutParams
            val textView_title_params = textView_title.layoutParams as RelativeLayout.LayoutParams

            /*imageView_profile_pic_params.addRule(  )
            imageView_profile_pic.layoutParams = imageView_profile_pic_params

            textView_title_params.addRule(  )
            textView_title.layoutParams = textView_title_params*/

            relativeLayout_cardView_content.animate()
                    .setDuration(200)
                    .translationY(-300f)
                    .setListener( MyAnimation(imageView_profile_pic, imageView_profile_pic_params, textView_title, textView_title_params, textView_body) )
                    .interpolator = FastOutSlowInInterpolator()


        }

    }
}

class MyAnimation(val imageView: ImageView, val imageParams: RelativeLayout.LayoutParams, val textView: TextView, val textParams: RelativeLayout.LayoutParams,val bodyText: TextView  ) : AnimatorListenerAdapter() {

    override fun onAnimationStart(animation: Animator?) {
        super.onAnimationStart(animation)

        if ( !isExpanded ) {
            imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_START, 0)

            textParams.addRule(RelativeLayout.BELOW, R.id.imageView_profile_pic)
            textParams.addRule(RelativeLayout.END_OF, 0)

            imageView.layoutParams = imageParams
            textView.layoutParams = textParams

            bodyText.visibility = View.VISIBLE

            isExpanded = true

        }else{

            imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL,0)
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_START)

            textParams.addRule(RelativeLayout.BELOW, 0)
            textParams.addRule(RelativeLayout.END_OF, R.id.imageView_profile_pic)

            imageView.layoutParams = imageParams
            textView.layoutParams = textParams

            bodyText.visibility = View.GONE

            isExpanded = false


        }

    }



}
