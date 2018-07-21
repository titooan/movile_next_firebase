package com.titouan.next.firebasemovilenext.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.titouan.next.firebasemovilenext.R

class MainActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity.javaClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
