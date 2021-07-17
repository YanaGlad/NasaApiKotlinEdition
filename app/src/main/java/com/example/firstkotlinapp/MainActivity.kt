package com.example.kotlintraining

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.RoversActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToRoversActivity(view: View?) {
        val i = Intent(this, RoversActivity::class.java)
        startActivity(i)
    }
}