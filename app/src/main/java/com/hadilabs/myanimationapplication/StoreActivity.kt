package com.hadilabs.myanimationapplication

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.hadilabs.myanimationapplication.fragment.StoreFragment
import com.hadilabs.myanimationapplication.recyclerview.item.StoreItem

import kotlinx.android.synthetic.main.activity_store.*
import java.io.Serializable

class StoreActivity : AppCompatActivity() {


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        //mSectionsPagerAdapter!!.feedsList = StoreFragment()

        val args = Bundle()
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

        args.putString("s", "s")
        args.putSerializable("items", items as Serializable)
        val newFragment = StoreFragment()
        newFragment.arguments = args

        Log.e("animaa", "feedlist: " + newFragment.arguments!!.getSerializable("items") + "\n string " + newFragment.arguments!!.getSerializable("s"))

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,StoreFragment())

        //mSectionsPagerAdapter!!.feedsList=  newFragment


        Log.e("animaa", "feedlist: " + mSectionsPagerAdapter!!.feedsList!!.realIems + "\n string " + mSectionsPagerAdapter!!.feedsList!!.s)

       // mSectionsPagerAdapter!!.notifyDataSetChanged()
        Log.e("animaa", "feedlist 2: " + mSectionsPagerAdapter!!.feedsList!!.realIems + "\n string " + mSectionsPagerAdapter!!.feedsList!!.s)
        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_store, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }



    inner class SectionsPagerAdapter(fm: FragmentManager, var feedsList: StoreFragment) : FragmentPagerAdapter(fm) {


        override fun getItem(position: Int): Fragment {

            Log.e("animaa", "feedlist: " + feedsList!!.realIems + " string: " + feedsList!!.s )

            return StoreFragment()  /*StoreFragment()*/
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }


}

 class ItemsArrays(val _myArray:ArrayList<ArrayList<String>>) : Serializable {

    companion object {
        const val serialVersionUID = java.security.Key.serialVersionUID
    }

}



