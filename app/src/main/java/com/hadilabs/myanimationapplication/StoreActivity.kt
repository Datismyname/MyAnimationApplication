package com.hadilabs.myanimationapplication

import android.net.Uri
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.Menu
import android.view.MenuItem
import com.hadilabs.myanimationapplication.fragment.StoreFragment
import com.hadilabs.myanimationapplication.model.Category
import com.hadilabs.myanimationapplication.recyclerview.item.StoreItem
import com.rahmadarifan.library.custombottomsheetbehavior.BottomSheetUtils
import com.rahmadarifan.library.custombottomsheetbehavior.CustomBottomSheetBehavior

import kotlinx.android.synthetic.main.activity_store.*
import java.io.Serializable
import android.support.annotation.NonNull
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_store.*
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_store.*


class StoreActivity : AppCompatActivity()  {


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mFragmentCardShadowTransformer: ShadowTransformer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        //setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        val item1 = StoreItem("Title 1" , "This is body number 1, we have to write some body text to make it as professional woke!")
        val item2 = StoreItem("Title 2" , "This is body number 2, we have to write some body text to make it as professional woke!")
        val item3 = StoreItem("Title 3" , "This is body number 3, we have to write some body text to make it as professional woke!")
        val item4 = StoreItem("Title 4" , "This is body number 4, we have to write some body text to make it as professional woke!")
        val item5 = StoreItem("Title 5" , "This is body number 5, we have to write some body text to make it as professional woke!")
        val item6 = StoreItem("Title 6" , "This is body number 6, we have to write some body text to make it as professional woke!")
        val item7 = StoreItem("Title 7" , "This is body number 7, we have to write some body text to make it as professional woke!")
        val item8 = StoreItem("Title 8" , "This is body number 8, we have to write some body text to make it as professional woke!")
        val item9 = StoreItem("Title 9" , "This is body number 9, we have to write some body text to make it as professional woke!")

        val items = arrayListOf(item1,item2,item3,item4,item5,item6,item7,item8, item9)


        val categories: MutableList<Category> = arrayListOf()
        val fragments: MutableList<StoreFragment> = arrayListOf()
        val arguments: ArrayList<Bundle> = arrayListOf()

        categories.add( Category( "Mobile Phones", items )  )
        categories.add( Category( "Chargers", items )  )
        categories.add( Category( "Power Banks", items )  )

        var i = 0
        for ( category in categories ){

            fragments.add( i,  StoreFragment() )
            arguments.add( i, Bundle() )

            arguments[i].putString( "categoryTitle" , category.title )
            arguments[i].putSerializable("categoryItems", category.items as Serializable)

            fragments[i].arguments = arguments[i]

            i += 1
        }


        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager , fragments )

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter


        // elevating currant fragment cards
        mFragmentCardShadowTransformer = ShadowTransformer(container, mSectionsPagerAdapter!!)

        container.setPageTransformer( false, mFragmentCardShadowTransformer )


        BottomSheetUtils.setupViewPager(container)

        CustomBottomSheetBehavior.from( bottom_sheet ).addBottomSheetCallback(
                object : CustomBottomSheetBehavior.BottomSheetCallback() {

                    override fun onStateChanged(bottomSheet: View, newState: Int) {

                        // React to state change

                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            //STATE_COLLAPSED
                            textView_expanding.text = "للصيانة إسحب للأسفل"
                        } else {
                            //STATE_EXPANDED;
                            textView_expanding.text = "للمشتريات إسحب للأعلى"

                        }

                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // React to dragging events
                    }

                }
        )

    }






    inner class SectionsPagerAdapter(fm: FragmentManager, private val feedsList: MutableList<StoreFragment>) : FragmentPagerAdapter(fm), CardAdapter  {


        override val baseElevation: Float
            get() = 3f


        override fun getTitleCardViewAt(position: Int): CardView {
            return feedsList.get(position).cardView_category_header
        }

        override fun getCardViewAt(position: Int): RecyclerView {
            return feedsList.get(position).recyclerView_fragment_store
        }


        override fun getItem(position: Int): Fragment {

            return feedsList[position]
        }

        override fun getCount(): Int {
            return feedsList.size
        }


        /*override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            feedsList.add(position , fragment as StoreFragment)
            return fragment
        }

        fun addCardFragment(fragment: StoreFragment) {
            feedsList.add(fragment)
        }*/


    }


}





