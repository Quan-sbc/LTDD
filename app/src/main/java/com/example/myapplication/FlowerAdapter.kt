package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlowerAdapter (private val list: List<String>):
    RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {
    class FlowerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTen: TextView=itemView.findViewById<TextView>(R.id.textView2)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowerAdapter.FlowerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_flower,parent,false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerAdapter.FlowerViewHolder, position: Int) {
        holder.txtTen.text=list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}