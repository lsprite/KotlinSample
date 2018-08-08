package com.lxb.kotlinsample.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.fastjson.JSON
import com.lxb.kotlinsample.MyApplication
import com.lxb.kotlinsample.R
import com.lxb.kotlinsample.adapter.CkAdapter
import com.lxb.kotlinsample.adapter.MainAdapter
import com.lxb.kotlinsample.bean.AdBean
import com.lxb.kotlinsample.bean.CkBean
import com.lxb.kotlinsample.bean.UserBean
import com.lxb.kotlinsample.bean.WaitOrderBean
import com.lxb.util.*
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.http.NameValuePair
import org.json.JSONObject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : BaseActivity() {
    val pool: ExecutorService = Executors.newFixedThreadPool(1)
    lateinit var adapter: MainAdapter
    lateinit var adapter2: CkAdapter
    var datas: ArrayList<CkBean> = ArrayList()
    var httpDatas: ArrayList<WaitOrderBean> = ArrayList()
    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SHOW_TOAST -> {
                    toast(msg.obj as String)
                }
                REFRESH_COMPLETE -> {
                    for (bean in httpDatas) {
                        LogUtil.log("----" + bean.id + "---" + bean.title)
                    }
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
        println(MyApplication.instance().lat)
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
        getinfo(AESUtils.EncryptAsDoNet("13700000004"))
        var bean: AdBean = AdBean()
        bean.id = "1"
        bean.id = "2"
        bean.id = "3"
        bean.id = "4"
        bean.url = "http://www.baidu.com"
        toast("Kotlin")
        //===listview
        var ck: CkBean = CkBean()
        ck.userName = "1"
        datas.add(ck)
        ck = CkBean()
        ck.userName = "2"
        datas.add(ck)
        adapter = MainAdapter(this)
        listview.adapter = adapter
        adapter.setList(datas)
        listview.setOnItemClickListener { parent, view, position, id ->
            try {
                toast(datas.get(position).userName)
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
        adapter2.setdatas(datas)
        adapter2.setOnItemClickLitener(object : CkAdapter.OnItemClickLitener {
            override fun onItemClick(view: View, position: Int) {
                try {
                    toast(datas.get(position).userName)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        println(PhoneInfo.getVersionName(this))
    }

    val pageSize: Int = 15
    fun getinfo(usermobile: String) {
        pool.execute {
            // TODO Auto-generated method stub
            try {
                val nameValuePairs = ArrayList<NameValuePair>()
                nameValuePairs.add(getNameValuePair("usermobile", usermobile))
                nameValuePairs.add(getNameValuePair("getkind", "getWaitOrderList"))
                nameValuePairs.add(getNameValuePair("pageIndex", 1.toString()))
                nameValuePairs.add(getNameValuePair("pageSize", pageSize.toString()))
                var retPost: String = HttpManager.posturl(nameValuePairs, URLUtil.URL + "waitorderinfo.aspx")
                if (retPost == null || retPost.trim().equals("error")
                        || retPost.trim().equals("")
                        || retPost.trim().equals("null")) {
                    postMsg(handler, "网络错误，请稍后再试", SHOW_TOAST)
                } else {
                    val dataJson = JSONObject(retPost)
                    val code = dataJson.getString("code")
                    val message = dataJson.getString("message")
                    val dataJsonArray = dataJson.getJSONArray("value")
                    if (code == URLUtil.SECCEED_CODE) {
                        var bean: WaitOrderBean
                        for (i in 0 until dataJsonArray.length()) {
                            val jsonObj = dataJsonArray.opt(i) as JSONObject
                            bean = JSON.parseObject(jsonObj.toString(), WaitOrderBean::class.java)
                            httpDatas.add(bean)
                        }
                        handler.sendEmptyMessage(BaseActivity.REFRESH_COMPLETE)
                    } else {
                        postMsg(handler, message, BaseActivity.SHOW_TOAST)
                    }
                }
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            } finally {
            }
        }
    }
}
