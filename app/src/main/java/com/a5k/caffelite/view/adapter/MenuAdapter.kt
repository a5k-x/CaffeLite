package com.a5k.caffelite.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.a5k.caffelite.R
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.databinding.ItemMenuBinding

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var listMenuCaffe = listOf<MenuCaffe>()
    lateinit var clickListenerItem: onClickAddOrder

    fun listenerItem(listener: onClickAddOrder) {
        this.clickListenerItem = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun init(listCaffe: List<MenuCaffe>) {
        this.listMenuCaffe = listCaffe
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val vb: ItemMenuBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(position: Int) {
            val name = listMenuCaffe[position].name
            val imageURL = listMenuCaffe[position].imageURL
            val price = listMenuCaffe[position].price
            val count = listMenuCaffe[position].count
            val imageLoader = ImageLoader.Builder(vb.root.context)
                .error(R.drawable.icon_error)
                .placeholder(R.drawable.icon_download_image)
                .build()

            vb.imageMenuItem.load(imageURL, imageLoader = imageLoader) {
                transformations(RoundedCornersTransformation(20F))
            }

            vb.priceCoffe.text = price.toString()
            vb.nameCaffe.text = name
            vb.orderCount.text = count.toString()
            val order = listMenuCaffe[position]
            var number = order.count
            vb.minusCount.setOnClickListener {
                if (number > 0) {
                    clickListenerItem.removeOrder(order)
                    number--
                    order.count = number
                    notifyItemChanged(position)
                }
            }
            vb.plusCount.setOnClickListener {
                clickListenerItem.addOrder(order)
                number++
                order.count = number
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount() = listMenuCaffe.size

}

interface onClickAddOrder {
    fun addOrder(order: MenuCaffe)
    fun removeOrder(order: MenuCaffe)
}