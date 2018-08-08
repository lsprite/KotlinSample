package com.lxb.kotlinsample.activity

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.widget.Toast
import org.apache.http.message.BasicNameValuePair
import java.net.URLEncoder

/**
 * Created by Administrator on 2017/5/25.
 */
open class BaseActivity : Activity() {
    companion object {
        val SHOW_TOAST = 100
        val REFRESH_COMPLETE = 101
        val REQUEST_PHOTO = 102
        val REQUEST_LOCALIMAGE = 103
    }

    override fun getResources(): Resources {
        var res = super.getResources()
        var config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.getDisplayMetrics())
        return res
    }

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    fun postMsg(handler: Handler, s: String, what: Int) {
        val msg = Message.obtain()
        msg.obj = s
        msg.what = what
        handler.sendMessage(msg)
    }

    fun postMsg(handler: Handler, t: Int, what: Int) {
        val msg = Message.obtain()
        msg.obj = t
        msg.what = what
        handler.sendMessage(msg)
    }

    fun getNameValuePair(key: String, value: String): BasicNameValuePair {
        if (TextUtils.isEmpty(value)) {
            return BasicNameValuePair(key, "")
        } else {
            return BasicNameValuePair(key, URLEncoder.encode(value, "utf-8"))
        }
    }
}