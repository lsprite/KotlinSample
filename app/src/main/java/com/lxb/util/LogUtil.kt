package com.lxb.util


import android.util.Log

object LogUtil {
    val LOG = true
    val TAG = "NEMO"
    @JvmStatic
    fun log(tag: String, msg: String) {
        var msg = msg
        if (msg == null)
            msg = "null"
        if (LOG) {
            Log.v(tag, msg)
        }
    }

    @JvmStatic
    fun log(msg: String) {
        var msg = msg
        if (msg == null)
            msg = "null"
        if (LOG) {
            Log.v(TAG, msg)
        }
    }
}
