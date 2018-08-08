package com.lxb.kotlinsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lxb.kotlinsample.R
import com.lxb.kotlinsample.bean.CkBean
import java.util.*

class CkAdapter constructor(context: Context) : RecyclerView.Adapter<CkAdapter.ViewHolder>() {
    var datas: ArrayList<CkBean> = ArrayList()
    lateinit var context: Context

    init {
        this.context = context
    }


    /**
     * 创建ViewHolder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = View.inflate(context, R.layout.item_main, null)
        val viewHolder = ViewHolder(view)
        viewHolder.tv = view.findViewById(R.id.tv) as TextView
        return viewHolder
    }

    /**
     * 设置值
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tv.text = datas[i].userName
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener {
                mOnItemClickLitener.onItemClick(viewHolder.itemView, i)
            }
        }
    }

    inner class ViewHolder(arg0: View) : RecyclerView.ViewHolder(arg0) {
        lateinit var tv: TextView
    }

    fun setdatas(datas: ArrayList<CkBean>) {
        if (datas != null) {
            this.datas = datas.clone() as ArrayList<CkBean>
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        // TODO Auto-generated method stub
        return if (datas == null) 0 else datas.size
    }

    /**
     * ItemClick的回调接口

     * @author zhy
     */
    interface OnItemClickLitener {
        fun onItemClick(view: View, position: Int)
    }

    lateinit var mOnItemClickLitener: OnItemClickLitener

    fun setOnItemClickLitener(mOnItemClickLitener: OnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener
    }
}
