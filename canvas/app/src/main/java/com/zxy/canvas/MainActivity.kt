package com.zxy.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(),OnScrollFinish{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<DevSeekBar>(R.id.devSeek).onScrollFinish=this@MainActivity
    }

    override fun onScroll(progress: Float) {
        Log.e("MainActivity", "onScroll: $progress")
    }
}