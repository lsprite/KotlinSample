package com.lxb.kotlinsample.bean

import java.io.Serializable

class WaitOrderBean() : Serializable {
    var id = 0// ds.Tables[0].Rows[i]["id"].ToString().Trim(),
    var title = ""// ds.Tables[0].Rows[i]["title"].ToString().Trim(),
    var do_userid = ""// ds.Tables[0].Rows[i]["do_userid"].ToString().Trim(),
    var type = ""// 1-请假,2-工作计划,3-工作评测
    var sid = ""// ds.Tables[0].Rows[i]["sid"].ToString().Trim(),
    var from_userid = ""// ds.Tables[0].Rows[i]["from_userid"].ToString().Trim(),
    var from_username = ""// ds.Tables[0].Rows[i]["from_username"].ToString().Trim(),
    var createtime = ""// ds.Tables[0].Rows[i]["createtime"].ToString().Trim()

    companion object {
        internal const val serialVersionUID = 1L
    }

}