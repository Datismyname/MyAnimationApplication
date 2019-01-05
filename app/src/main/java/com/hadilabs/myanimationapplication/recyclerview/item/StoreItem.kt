package com.hadilabs.myanimationapplication.recyclerview.item

import com.hadilabs.myanimationapplication.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_store.*

class StoreItem(val title: String, val body:String): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView_title.text = title
        viewHolder.textView_body.text = body

    }

    override fun getLayout() = R.layout.item_store


}