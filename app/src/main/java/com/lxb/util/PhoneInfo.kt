package com.lxb.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.widget.Toast

/**
 * Created by Administrator on 2017/5/26.
 */

object PhoneInfo {
    /**
     * @return
     * *
     * @description:当前运行Activity的名称
     * *
     * @author:Sunny
     * *
     * @return:String
     */
    fun getRunningActivityName(context: Context): String {
        val contextString = context.toString()
        return contextString.substring(contextString.lastIndexOf(".") + 1,
                contextString.indexOf("@")) + "："
    }

    /**
     * 获取系统版本号

     * @return
     * *
     * @throws Exception
     */
    fun getVersionCode(context: Context): Int {
        try {
            // 获取packagemanager的实例
            val packageManager = context.packageManager
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            val packInfo = packageManager
                    .getPackageInfo(context.packageName,
                            PackageManager.GET_CONFIGURATIONS)
            return packInfo.versionCode
        } catch (e: Exception) {
            return 0
        }

    }

    /**
     * 获取软件版本号
     */
    fun getVersionName(context: Context): String {
        // 获取packagemanager的实例
        val packageManager = context.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        var packInfo: PackageInfo? = null
        try {
            packInfo = packageManager.getPackageInfo(context.packageName,
                    0)
        } catch (e: PackageManager.NameNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        val version = packInfo!!.versionName
        return version
    }

    // ----------------------屏幕-----------------------------

    /**
     * 获取屏幕宽

     * @param context
     * *
     * @return
     */
    fun getDisplayWidthMetrics(context: Context): Int {
        var dm = DisplayMetrics()
        dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 获取屏幕高

     * @param context
     * *
     * @return
     */
    fun getDisplayHeightMetrics(context: Context): Int {
        var dm = DisplayMetrics()
        dm = context.resources.displayMetrics
        return dm.heightPixels
    }

    fun isNetAlive(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null) {
            if (!networkInfo.isAvailable) {
                Toast.makeText(context, "网络状况不好，请稍后再试！", Toast.LENGTH_SHORT)
                        .show()
                return false
            }
        } else {
            Toast.makeText(context, "网络没开启", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
