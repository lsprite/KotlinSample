package com.lxb.util

/**
 * Created by Administrator on 2017/5/19.
 */

import android.util.Log
import org.apache.http.HttpVersion
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.entity.mime.content.StringBody
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.params.CoreProtocolPNames
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


object HttpManager {

    var IS_DEBUG = true

    fun posturl(nameValuePairs: ArrayList<NameValuePair>,
                url: String): String {
        var tmp = ""
        var `is`: InputStream? = null
        try {
            val httpclient = DefaultHttpClient()
            val httppost = HttpPost(url)
            httppost.entity = UrlEncodedFormEntity(nameValuePairs)
            var logString = url + "?"
            for (i in nameValuePairs.indices) {
                val nameValuePair = nameValuePairs[i]
                if (i == nameValuePairs.size - 1) {
                    logString += nameValuePair.name + "=" + nameValuePair.value
                } else {
                    logString += nameValuePair.name + "=" + nameValuePair.value + "&"
                }
                if (IS_DEBUG) {
                    System.err.println(nameValuePair.name + "="
                            + nameValuePair.value)
                }
            }
            if (IS_DEBUG) {
                Log.w("HttpManager", "HttpManager Post:" + logString)
            }
            val response = httpclient.execute(httppost)
            val entity = response.entity
            `is` = entity.content
        } catch (e: Exception) {
            return "error"
        }

        try {
            val reader = BufferedReader(InputStreamReader(
                    `is`!!, "UTF-8"))
            val sb = StringBuilder()
            var line: String? = null
            while (true) {
                line = reader.readLine()
                if (line != null) {
                    sb.append(line!! + "\n")
                } else {
                    break
                }
            }
            `is`.close()
            tmp = sb.toString()
        } catch (e: Exception) {
            return "error"
        }

        LogUtil.log(tmp)
        return tmp
    }

    fun uploadFiles(url: String,
                    params: Map<String, String>, filesMap: Map<String, File>): String {
        var tmp = ""
        var `is`: InputStream? = null
        try {
            val httpClient = DefaultHttpClient()
            httpClient.params.setParameter(
                    CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1)
            val httpRequest = HttpPost(url)
            val mEntity = MultipartEntity()
            // 添加上传参数
            for ((key, value) in params) {
                val strBody = StringBody(value)
                mEntity.addPart(key, strBody)
                System.err.println(key + "=" + value)
            }
            // 添加上传的文件
            val iterator = filesMap.keys.iterator()
            while (iterator.hasNext()) {
                val fileName = iterator.next()
                val cBody = FileBody(filesMap[fileName])
                mEntity.addPart(fileName, cBody)
                System.err.println("while-----iterator fileName:" + fileName
                        + "  file:" + filesMap[fileName])
            }
            httpRequest.entity = mEntity
            val httpResponse = httpClient.execute(httpRequest)
            val resEntity = httpResponse.entity
            `is` = resEntity.content
        } catch (e: Exception) {
            return "error"
        }

        try {
            val reader = BufferedReader(InputStreamReader(
                    `is`!!, "UTF-8"))
            val sb = StringBuilder()
            var line: String? = null
            while (true) {
                line = reader.readLine()
                if (line != null) {
                    sb.append(line!! + "\n")
                } else {
                    break;
                }
            }
            `is`.close()
            tmp = sb.toString()
        } catch (e: Exception) {
            return "error"
        }
        LogUtil.log(tmp)
        return tmp
    }
}