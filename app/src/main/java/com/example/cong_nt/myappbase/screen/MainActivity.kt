package com.example.cong_nt.myappbase.screen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.cong_nt.myappbase.R
import com.example.cong_nt.myappbase.base.retrofit.RetrofitRxBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitRxBuilder.Builder()
                .setLanguage("vi")
                .url("/")
                .showLoging(true)
                .sslMode(true)
                .build()

        startActivity(Intent(this, ActivityTwo::class.java))
        finish()
    }
}
