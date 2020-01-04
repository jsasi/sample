package com.asi.splashguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity()  {

    /**
     * CountDownTimer 实现倒计时
     */
    private val countDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onFinish() {
            Intent(this@SplashActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            (millisUntilFinished / 1000).toString().let {
                tvCountDown.text = it
            }
        }
    };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_splash)
//        countDownTimer.start()
        MainScope().launch {
            repeat(50,{
                delay(1000)
                Log.e("SplashActivity","onCreate(SplashActivity.java:41==$it)"+Thread.currentThread().name)
            })

        }
    }
}
