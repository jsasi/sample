package com.asi.handle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val debouncer = Debouncer(2000) { text ->
            Log.e("MainActivity", "onCreate(MainActivity.java:13==$text)")
        }

        editText.onChange { text ->
            debouncer.process(text)
        }

        val debounce = Debounce(3000) {
            Log.e("MainActivity", "onCreate(MainActivity.java:13==${editText.text.toString()})")
        }

        val throttler = Throttler(3000) {
            Log.e("MainActivity", "onCreate(MainActivity.java:22==${System.currentTimeMillis()})")
        }
        button.setOnClickListener { throttler.onAction() }


    }


}
