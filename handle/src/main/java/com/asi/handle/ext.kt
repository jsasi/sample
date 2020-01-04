package com.asi.handle

import android.icu.util.TimeUnit
import android.os.Handler
import android.os.Looper

/*****************************************************************************************
 *
 * @Project:sample
 * @Auther:Asi
 * @Date:2020/1/3
 * @Description:
 *
 ****************************************************************************************/
const val MESSAGE_WHAT = 1

class Debouncer(
    private val timeout: Long,
    private val callback: (String) -> Unit

) {
    private val handler = Handler(Looper.getMainLooper()) { message ->
        if (message.what != MESSAGE_WHAT) {
            return@Handler false
        }
        callback(message.obj as String)
        true
    }

    fun process(text: String) {
        handler.removeMessages(MESSAGE_WHAT)
        val message = handler.obtainMessage(MESSAGE_WHAT, text)
        handler.sendMessageDelayed(message, timeout)
    }
}

class Throttler(
    private val timeout: Long,
    private val callback: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    fun onAction() {
        if (handler.hasMessages(MESSAGE_WHAT)) {
            return
        }
        val message = handler.obtainMessage(MESSAGE_WHAT)
        handler.sendMessageDelayed(message, timeout)
        callback()
    }
}

class Debounce(
    //间隔时间
    private val timeout: Long,
    //回调
    private val callback: () -> Unit

) {
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable(callback)

    //触发防抖
    fun process() {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, timeout)
    }
}