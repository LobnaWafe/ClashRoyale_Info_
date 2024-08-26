package com.example.clashroyalinfo_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter2() : RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {
    //////////////////////



    //////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fav_card_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Fav_imageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem= Fav_imageList[position]
        holder.image_1.setImageResource(currentItem)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_1:ShapeableImageView=itemView.findViewById(R.id.imade_1_0)

    }
}