package com.lxb.kotlinsample.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lxb.kotlinsample.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Administrator on 2018/8/8.
 */
class LoginActivity : BaseActivity() {
    val pool: ExecutorService = Executors.newFixedThreadPool(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun myClick(view: View) {
        var intent: Intent
        when (view.id) {
            R.id.btn -> {
                intent = Intent(LoginActivity@ this, MainActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(0, 0)
            }
            else -> {
            }
        }
    }
}