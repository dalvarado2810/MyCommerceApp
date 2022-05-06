package com.example.mycommerceapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mycommerceapp.R
import com.example.mycommerceapp.data.model.Result
import com.example.mycommerceapp.databinding.CommerceItemBinding
import com.example.mycommerceapp.ui.view.ManagerCallback
import com.example.mycommerceapp.ui.view.utils.getString
import com.example.mycommerceapp.ui.view.adapter.diffutil.ItemDiffUtil

class SearchItemsAdapter(val callback: ManagerCallback) :
    ListAdapter<Result, SearchItemsAdapter.ItemViewHolder>(ItemDiffUtil){

    lateinit var binding: CommerceItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.commerce_item,
                parent,
                false)
        binding = CommerceItemBinding.bind(view)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ItemViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {
        private val imageItem = binding.itemImage
        private val itemTittle= binding.itemTittle
        private val itemPrice = binding.itemPrice
        private val itemDiscount = binding.itemDiscount
        private val itemInstallments = binding.installmentsItem
        private val itemDelivery = binding.itemDelivery
        private val itemProvider = binding.itemProvider
        private val cardView = binding.itemCardview

        fun bind(item : Result) {

            imageItem.load(item.thumbnail){
                crossfade(true)
                placeholder(R.drawable.ic_loading)
            }
            itemTittle.text = item.title
            itemPrice.text = itemView.getString(
                R.string.price,
                item.price!!.toInt())
            if (item.condition == R.string.new_condition.toString()){
                itemDiscount.text = itemView.getString(R.string.condition_new)
            } else {
                itemDiscount.text = itemView.getString(R.string.condition_used)
            }
            if (item.installments?.quantity == null){
                itemInstallments.visibility = View.GONE
            } else {
                itemInstallments.text = itemView.getString(
                    R.string.installments,
                    item.installments?.quantity.toString(),
                    item.installments?.amount.toString()
                )
            }
            if (item.shipping?.free_shipping == true){
                itemDelivery.text = itemView.getString(R.string.free_shipping)
            } else {
                itemDelivery.text = itemView.getString(R.string.pay_shipping)
            }
            if (item.accepts_mercadopago == true) {
                itemProvider.visibility = View.VISIBLE
            }
            cardView.setOnClickListener {
                callback.onItemClicked(item)
            }
        }
    }
}