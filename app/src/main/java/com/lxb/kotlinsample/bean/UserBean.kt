package com.lxb.kotlinsample.bean

import java.io.Serializable

/**
 * Created by Administrator on 2017/10/19.
 */
class UserBean() : Serializable {
    var id = 0// 6",
    var userName = "空位"// 李四",
    var mobile = "00000000000"// 13700000002"

    companion object {
        internal const val serialVersionUID = 1L
    }

}