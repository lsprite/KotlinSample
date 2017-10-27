package com.lxb.kotlinsample.bean

/**
 * Created by Administrator on 2017/10/27.
 */
class KotlinSingleton {
    companion object {
        private val kotlinSingleton = KotlinSingleton()

        @JvmStatic
        fun getInstance() = kotlinSingleton
    }
}