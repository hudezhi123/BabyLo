package com.hdz.image.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hdz.image.R
import com.hdz.image.bean.ImgText

class ImgTextAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context
    var dataList: MutableList<ImgText>

    init {
        this.context = context;
        dataList = mutableListOf()
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int = 0, imgText: ImgText)
    }


    fun setData(dataList: MutableList<ImgText>) {
        this.dataList = dataList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_grid_img_text_layout, parent, false)
        return ImgTextHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = dataList.size ?: 0

    inner class ImgTextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var img: ImageView

        lateinit var text: TextView

        init {
            itemView.setOnClickListener({
                val position = layoutPosition
                onItemClickListener?.onItemClick(position, dataList.get(position))
            })
        }

    }
}