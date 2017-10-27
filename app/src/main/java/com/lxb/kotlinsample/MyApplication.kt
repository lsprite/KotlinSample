package com.lxb.kotlinsample

import android.app.Application

/**
 * Created by Administrator on 2017/5/19.
 */
class MyApplication : Application() {
    var lat: Double = 0.0
    var lon: Double = 0.0

    companion object {
        private var instance: MyApplication? = null

        @JvmStatic
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        lat = 1.0
    }
}