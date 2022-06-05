package com.a5k.caffelite.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a5k.caffelite.databinding.ItemCaffeBinding
import com.a5k.caffelite.domain.entity.Caffe

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listPointCaffe = listOf<Caffe>()
    lateinit var clickListenerItem: OnClick

    fun listenerItem(listener: OnClick) {
        this.clickListenerItem = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun settingAdapter(listCaffe: List<Caffe>) {
        this.listPointCaffe = listCaffe
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val vb: ItemCaffeBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(position: Int) {
            vb.nameCaffe.text = listPointCaffe[position].name
            itemView.setOnClickListener {
                val idCaffe = listPointCaffe[position].id
                clickListenerItem.onHandlerClickLister(idCaffe)
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

interface OnClick {
    fun onHandlerClickLister(idCaffe: Int)
}