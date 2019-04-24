package com.cristhian.statify.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.REDIRECT_URI
import com.cristhian.statify.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main)


    }
}
