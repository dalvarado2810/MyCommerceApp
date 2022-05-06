package com.example.mycommerceapp.ui.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.mycommerceapp.data.model.Result

object ItemDiffUtil : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}