package com.lxb.kotlinsample.activity

import android.os.Bundle
import com.lxb.kotlinsample.R
import com.lxb.kotlinsample.bean.UserBean
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {
    var ub: UserBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        ub = getIntent().getSerializableExtra("ub") as UserBean?
        if (ub != null) {
            tv.text = ub!!.userName
        }

    }
}
