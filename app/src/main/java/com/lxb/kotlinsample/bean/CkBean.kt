package com.lxb.kotlinsample.bean

import java.io.Serializable

/**
 * 乘客
 */
class CkBean : Serializable {
    var id = ""// 6",
    var userid = ""// 2",
    var carpool_id = ""// 2",
    var createtime = ""// 2017-05-09 11:07:01",
    var userName = "空位"// 李四",
    var mobile = "00000000000"// 13700000002"

    companion object {
        internal const val serialVersionUID = 1L
    }

}// TODO Auto-generated constructor stub
