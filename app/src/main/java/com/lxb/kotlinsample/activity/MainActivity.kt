package com.lxb.kotlinsample.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.fastjson.JSON
import com.lxb.kotlinsample.R
import com.lxb.kotlinsample.adapter.CkAdapter
import com.lxb.kotlinsample.adapter.MainAdapter
import com.lxb.kotlinsample.bean.AdBean
import com.lxb.kotlinsample.bean.CkBean
import com.lxb.kotlinsample.bean.UserBean
import com.lxb.util.HttpManager
import com.lxb.util.PhoneInfo
import com.lxb.util.SyLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.http.NameValuePair
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : BaseActivity() {
    var pool: ExecutorService? = null
    var adapter: MainAdapter? = null
    var adapter2: CkAdapter? = null
    var datas: ArrayList<CkBean>? = ArrayList()
    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SHOW_TOAST -> {
                    toast(msg.obj as String)
                }
                REFRESH_COMPLETE -> {
                }
                else -> {
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        var res = "{\"id\": 100,\"userName\": \"Mike\",\"mobile\": \"00000000000\"}";
        var ub = JSON.parseObject(res,
                UserBean::class.java)
        btn.setOnClickListener {
            var intent: Intent = Intent(MainActivity@ this, SecondActivity::class.java)
            intent.putExtra("ub", ub)
            startActivity(intent)
        }
        //
        tv.text = "Kotlin Sample"
        getinfo()
        var bean: AdBean = AdBean()
        bean.id = "1"
        bean.id = "2"
        bean.url = "http://www.baidu.com"
        toast("Kotlin")
        //===listview
        var ck: CkBean = CkBean()
        ck.userName = "1"
        datas!!.add(ck)
        ck = CkBean()
        ck.userName = "2"
        datas!!.add(ck)
        adapter = MainAdapter(this)
        listview.adapter = adapter
        adapter!!.setList(datas)
        listview.setOnItemClickListener { parent, view, position, id ->
            try {
                toast(datas!!.get(position).userName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //===recyclerview
        adapter2 = CkAdapter(this)
        val linearLayoutManager = SyLinearLayoutManager(
                this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = adapter2
        adapter2!!.setdatas(datas)
        adapter2!!.setOnItemClickLitener(object : CkAdapter.OnItemClickLitener {
            override fun onItemClick(view: View, position: Int) {
                try {
                    toast(datas!!.get(position).userName)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        println(PhoneInfo.getVersionName(this))
    }


    fun getinfo() {
        if (pool == null) {
            pool = Executors.newFixedThreadPool(1)
        }
        pool!!.execute {
            // TODO Auto-generated method stub
            try {
                val nameValuePairs = ArrayList<NameValuePair>()
                var retPost: String? = HttpManager.posturl(nameValuePairs, "http://192.168.1.1/")
                if (retPost == null || retPost.trim().equals("error")
                        || retPost.trim().equals("")
                        || retPost.trim().equals("null")) {
                } else {
                }
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            } finally {
            }
        }
    }
}
