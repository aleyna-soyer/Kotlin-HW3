package com.aleyna.kotlin_hw3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleyna.kotlin_hw3.R
import com.aleyna.kotlin_hw3.databinding.ItemProductBinding
import com.aleyna.kotlin_hw3.model.Products
import com.bumptech.glide.Glide

class ProductAdapter(var productsList: ArrayList<Products>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(var view: ItemProductBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemProductBinding>(inflater, R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.titleTV.text = productsList[position].title
        holder.view.priceTV.text = productsList[position].price.toString()
        Glide.with(holder.view.root)
            .load(productsList[position].productUrl)
            .into(holder.view.productIV)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Products>){
        productsList= newList as ArrayList<Products>
        notifyDataSetChanged()
    }
}