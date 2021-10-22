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
import com.hdz.image.viewmodel.OnItemClickListener

class AnimalAdapter(context: Context, itemClick: OnItemClickListener<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.ImgTextHolder>() {

    var context: Context
    var dataList: MutableList<Animal>
    var itemClick: OnItemClickListener<Animal>


    init {
        this.context = context;
        dataList = mutableListOf()
        this.itemClick = itemClick
    }

    fun setData(dataList: MutableList<Animal>) {
        this.dataList = dataList
        for (index in 0 until dataList.size - 1) {
            dataList.get(index).position = index
        }
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
        holder.bind.setVariable(BR.animal, dataList.get(position))
        holder.bind.executePendingBindings()
        holder.bind.itemClick = itemClick
    }

    override fun getItemCount(): Int = dataList.size

    inner class ImgTextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var bind: ItemGridImgTextLayoutBinding


    }
}