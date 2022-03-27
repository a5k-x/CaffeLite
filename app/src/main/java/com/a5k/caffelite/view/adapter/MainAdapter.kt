package com.a5k.caffelite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.databinding.ItemCaffeBinding
import com.a5k.caffelite.model.PointCaffe

class MainAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listPointCaffe = listOf<PointCaffe>()

    fun init(listCaffe:List<PointCaffe>){
        this.listPointCaffe = listCaffe
    }

   inner class ViewHolder(val vb:ItemCaffeBinding):RecyclerView.ViewHolder(vb?.root){
       fun bind(position: Int) {

       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ViewHolder(ItemCaffeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       (holder as ViewHolder).bind(position)
    }

    override fun getItemCount()= listPointCaffe.size


}