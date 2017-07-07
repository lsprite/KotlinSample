package com.lxb.kotlinsample.bean

import java.io.Serializable

/**
 * Created by Administrator on 2017/5/23.
 */
class AdBean : Serializable {
  var id = ""
        get() {
            println("---get:"+field)
            if (field.equals(""))
                return "get id is null"
            else
                return field
        }
        set(value) {
            println("---set.value:"+value)
            println("---set.field:"+field)
            if (value.equals(""))
                field = "set id not null"
            else
                field = value
        }
    var url = ""

    companion object {
        internal const val serialVersionUID = 1L
    }
}