package com.example.clashroyalinfo_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val cardslist: ArrayList<Cards>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//////////////////////
 lateinit var mListener : onItemClickListner
  interface onItemClickListner
  {
      fun onItemClick(position: Int)
  }
    fun onItemClickListner(listner: onItemClickListner)
    {
        mListener = listner
    }
//////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false)
        return MyViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int {
        return cardslist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem=cardslist[position]
        holder.image_1.setImageResource(currentItem.titleimage_1)
    }
    class MyViewHolder(itemView: View,listner: onItemClickListner) : RecyclerView.ViewHolder(itemView) {
        var image_1:ShapeableImageView=itemView.findViewById(R.id.imade_1_0)
      init {
          itemView.setOnClickListener {
              listner.onItemClick(adapterPosition)
          }
      }
    }
}