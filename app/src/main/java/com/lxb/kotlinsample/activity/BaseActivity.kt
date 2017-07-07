package com.lxb.kotlinsample.activity

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.widget.Toast

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
}