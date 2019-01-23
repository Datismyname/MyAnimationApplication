package com.hadilabs.myanimationapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_dialog.*


class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        //val screenWidth = resources.displayMetrics.widthPixels
        /*val layout = findViewById<FrameLayout>(R.id.relativeLayout_cardView_content)
        layout.layoutParams.width = screenWidth / 3
        layout.layoutParams.height = screenWidth / 3
        layout.requestLayout()*/


        (relativeLayout_cardView_content.parent as View).setOnClickListener {

            ActivityCompat.finishAfterTransition(this)


        }



        relativeLayout_cardView_content.setOnClickListener(null)


        val position = intent.getIntExtra("position", 0)
        val imagePath = intent.getIntExtra("imagePath", 0)
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        imageView_profile_pic.setImageResource( imagePath )
        textView_title.text = title
        textView_body.text = body


    }
}
