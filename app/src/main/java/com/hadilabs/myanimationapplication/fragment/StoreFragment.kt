package com.hadilabs.myanimationapplication.fragment



import android.app.Activity

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat

import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.support.v4.util.Pair
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.hadilabs.myanimationapplication.R
import com.hadilabs.myanimationapplication.recyclerview.item.StoreItem

import kotlinx.android.synthetic.main.fragment_store.view.*

import android.widget.TextView
import com.hadilabs.myanimationapplication.DialogActivity
import java.io.Serializable


class StoreFragment : Fragment() {

    var realIems:ArrayList<StoreItem>? = null
    var headTitle : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            headTitle = it.getString("s")
            realIems = it.getSerializable("items")  as ArrayList<StoreItem>
        }
    }


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
        val item9 = StoreItem("Title 9" , "This is body number 9, we have to write some body text to make it as professional woke!")

        val items = mutableListOf(item1,item2,item3,item4,item5,item6,item7,item8, item9)

        Log.e("animaa", "string: $headTitle" )

        val daysArray = arrayOfNulls<String>(items.size)
        val datesArray = arrayOfNulls<String>(items.size)

        for (i in 0 until daysArray.size) {
            daysArray[i] = realIems!![i].title
            datesArray[i] = realIems!![i].body
        }

        view.textView_category_title.text = headTitle

        view.recyclerView_fragment_store.apply {

            layoutManager = LinearLayoutManager( context )

            adapter = RecyclerAdapter(daysArray, datesArray)


        }

        return view
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: Serializable) =
                StoreFragment().apply {
                    arguments = Bundle().apply {
                        putString("s", param1)
                        putSerializable("items", param2)
                    }
                }
    }

}




class RecyclerAdapter( private var daysArray: Array<String?>, private var datesArray: Array<String?>) : RecyclerView.Adapter<RecyclerAdapter.MyCardViewHolder>() {


    private var mContext: Context? = null
    private var mDaysTxt: TextView? = null
    private var mDateTxt: TextView? = null
    private var mDateContainerLayout: CardView? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardViewHolder {

        mContext = parent.context

        val layoutInflater: LayoutInflater? = LayoutInflater.from(mContext)

        val view = layoutInflater!!.inflate(R.layout.item_store, parent, false)


        return MyCardViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyCardViewHolder, position: Int) {

        mDaysTxt = holder.mDaysTxt
        mDateTxt = holder.mDateTxt
        mDateContainerLayout = holder.mDateContainerLayout

        mDaysTxt!!.text = daysArray[position]
        mDateTxt!!.text = datesArray[position]


    }

    override fun getItemCount(): Int {
        return daysArray.size
    }




    inner class MyCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mDaysTxt: TextView? = null
        var mDateTxt: TextView? = null
        private var mImageView: ImageView? = null
        var mDateContainerLayout: CardView? = null


        init {
            mDaysTxt = itemView.findViewById (R.id.textView_title) as TextView
            mDateTxt = itemView.findViewById (R.id.textView_body) as TextView
            mImageView = itemView.findViewById( R.id.imageView_profile_pic ) as ImageView
            mDateContainerLayout = itemView.findViewById (R.id.relativeLayout_cardView_content) as CardView


            mDateContainerLayout!!.setOnClickListener {


                val i =  Intent( Intent( mContext, DialogActivity::class.java ) )

                val imagePath = R.drawable.ic_baseline_stars_24px

                i.putExtra("imagePath", imagePath)
                i.putExtra("title", mDaysTxt!!.text)
                i.putExtra("body", mDateTxt!!.text)


                val pairs = ArrayList< Pair<View, String> >()

                pairs.add( Pair( mImageView!!, "profileImageTransition" ) )
                pairs.add( Pair( mDaysTxt!!, "titleTransition" ) )
                pairs.add( Pair( mDateTxt!!, "bodyTransition" ) )
                pairs.add( Pair( itemView , "transition" ) )

                val pairsArray:Array< Pair<View, String> > = pairs.toTypedArray()

                val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation( (mContext as Activity), *pairsArray)
                    (mContext as Activity).startActivity(i, options.toBundle())



            }
        }
    }

    }