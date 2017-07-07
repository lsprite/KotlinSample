package com.lxb.kotlinsample.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.lxb.kotlinsample.R
import com.lxb.kotlinsample.bean.CkBean
import java.util.*

/**
 * Created by Administrator on 2017/5/26.
 */

class MainAdapter constructor(context: Context?) : BaseAdapter() {
    private var datas: ArrayList<CkBean>? = ArrayList()
    private var context: Context? = null

    init {
        this.context = context
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return if (datas == null) 0 else datas!!.size
    }

    override fun getItem(paramInt: Int): Any {
        // TODO Auto-generated method stub
        return datas!![paramInt]
    }

    override fun getItemId(paramInt: Int): Long {
        // TODO Auto-generated method stub
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        // TODO Auto-generated method stub
        var v: View? = null
        try {
            if (convertView != null) {
                v = convertView
            } else {
                v = View.inflate(context, R.layout.item_main, null)
                val ic = ItemControls()
                ic.tv = v!!.findViewById(R.id.tv) as TextView//
                v.tag = ic
            }
            val ic = v!!.tag as ItemControls
            ic.tv!!.text = datas!!.get(position).userName
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

        return v
    }

    internal inner class ItemControls {
        var tv: TextView? = null
    }

    fun setList(datas: ArrayList<CkBean>?) {
        if (datas != null) {
            this.datas = datas.clone() as ArrayList<CkBean>
            notifyDataSetChanged()
        }
    }

}
