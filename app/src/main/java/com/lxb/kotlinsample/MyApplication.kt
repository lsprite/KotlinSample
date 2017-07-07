package com.lxb.kotlinsample

import android.app.Application

/**
 * Created by Administrator on 2017/5/19.
 */
class MyApplication : Application() {
    companion object {
        private var instance: MyApplication? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}