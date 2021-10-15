package com.hdz.image.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hdz.base.bean.Animal
import com.hdz.image.BR
import com.hdz.image.R
import com.hdz.image.databinding.ItemGridImgTextLayoutBinding

class AnimalAdapter(context: Context) : RecyclerView.Adapter<AnimalAdapter.ImgTextHolder>() {

    var context: Context
    var dataList: MutableList<Animal>

    init {
        this.context = context;
        dataList = mutableListOf()
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int = 0, animal: Animal)
    }


    fun setData(dataList: MutableList<Animal>) {
        this.dataList = dataList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgTextHolder {

        val bind: ItemGridImgTextLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_grid_img_text_layout,
            parent,
            false
        )
        val holder = ImgTextHolder(bind.root)
        holder.bind = bind
        return holder
    }

    override fun onBindViewHolder(holder: ImgTextHolder, position: Int) {
        holder.bind.setVariable(BR.imgText,dataList.get(position))
        holder.bind.executePendingBindings()
    }

    override fun getItemCount(): Int = dataList.size

    inner class ImgTextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var bind: ItemGridImgTextLayoutBinding

        init {
            itemView.setOnClickListener({
                val position = layoutPosition
                onItemClickListener?.onItemClick(position, dataList.get(position))
            })
        }

    }


}