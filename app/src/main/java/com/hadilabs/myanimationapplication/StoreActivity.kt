package com.hadilabs.myanimationapplication

import android.net.Uri
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hadilabs.myanimationapplication.fragment.StoreFragment
import com.hadilabs.myanimationapplication.model.Category
import com.hadilabs.myanimationapplication.recyclerview.item.StoreItem
import com.rahmadarifan.library.custombottomsheetbehavior.BottomSheetUtils

import kotlinx.android.synthetic.main.activity_store.*
import java.io.Serializable

class StoreActivity : AppCompatActivity()  {


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

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


        val categories: ArrayList<Category> = arrayListOf()
        val fragments: ArrayList<StoreFragment> = arrayListOf()
        val arguments: ArrayList<Bundle> = arrayListOf()

        categories.add( Category( "Mobile Phones", items )  )
        categories.add( Category( "Chargers", items )  )

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

        BottomSheetUtils.setupViewPager(container)


    }






    inner class SectionsPagerAdapter(fm: FragmentManager, private val feedsList: List<Fragment>) : FragmentPagerAdapter(fm) {


        override fun getItem(position: Int): Fragment {

            return feedsList[position]
        }

        override fun getCount(): Int {
            return feedsList.size
        }
    }


}





