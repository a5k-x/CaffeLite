package com.a5k.caffelite.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.databinding.ItemCaffeBinding

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listPointCaffe = listOf<PointCaffe>()
    lateinit var clickListenerItem: OnClickCust

    fun listenerItem(listener: OnClickCust) {
        this.clickListenerItem = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun init(listCaffe: List<PointCaffe>) {
        this.listPointCaffe = listCaffe
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val vb: ItemCaffeBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(position: Int) {
            vb?.nameCaffe.text = listPointCaffe[position].name
            itemView.setOnClickListener {
                val idPointCaffe = listPointCaffe[position].id
                clickListenerItem.onHandlerClickLister(idPointCaffe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemCaffeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount() = listPointCaffe.size
}

interface OnClickCust {
    fun onHandlerClickLister(idPointCaffe: Int)
}