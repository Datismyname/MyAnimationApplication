package com.hadilabs.myanimationapplication.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.hadilabs.myanimationapplication.R
import com.hadilabs.myanimationapplication.recyclerview.item.StoreItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.item_store.*


var isExpanded = false


class StoreFragment : Fragment() {

    private var storeCategorySection: Section? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_store, container, false)

        val item1 = StoreItem("Title 1" , "This is body number 1, we have to write some body text to make it as professional woke!")
        val item2 = StoreItem("Title 2" , "This is body number 2, we have to write some body text to make it as professional woke!")
        val item3 = StoreItem("Title 3" , "This is body number 3, we have to write some body text to make it as professional woke!")
        val item4 = StoreItem("Title 4" , "This is body number 4, we have to write some body text to make it as professional woke!")
        val item5 = StoreItem("Title 5" , "This is body number 5, we have to write some body text to make it as professional woke!")
        val item6 = StoreItem("Title 6" , "This is body number 6, we have to write some body text to make it as professional woke!")
        val item7 = StoreItem("Title 7" , "This is body number 7, we have to write some body text to make it as professional woke!")
        val item8 = StoreItem("Title 8" , "This is body number 8, we have to write some body text to make it as professional woke!")

        val items = mutableListOf(item1,item2,item3,item4,item5,item6,item7,item8)


        view.recyclerView_fragment_store.apply {

            layoutManager = LinearLayoutManager( context )

            adapter = GroupAdapter<ViewHolder>().apply {

                storeCategorySection = Section(items)

                add(storeCategorySection!!)

                setOnItemClickListener( onItemClick )

            }

        }

        return view
    }



    val onItemClick = OnItemClickListener{ item, view ->


        if ( item is StoreItem ){


            TransitionManager.beginDelayedTransition( relativeLayout_cardView_content )

            val imageView_profile_pic_params = imageView_profile_pic.layoutParams as RelativeLayout.LayoutParams
            val textView_title_params = textView_title.layoutParams as RelativeLayout.LayoutParams


            relativeLayout_cardView_content.animate()
                    .setDuration(200)
                    .translationY(-1f)
                    .setListener( MyAnimation(imageView_profile_pic, imageView_profile_pic_params, textView_title, textView_title_params, textView_body) )
                    .interpolator = FastOutSlowInInterpolator()





        }



    }


}


class MyAnimation(val imageView: ImageView, val imageParams: RelativeLayout.LayoutParams, val textView: TextView, val textParams: RelativeLayout.LayoutParams, val bodyText: TextView) : AnimatorListenerAdapter() {

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