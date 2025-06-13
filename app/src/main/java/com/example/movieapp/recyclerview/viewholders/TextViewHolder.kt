package com.example.movieapp.recyclerview.viewholders

import android.util.Log
import com.example.movieapp.databinding.RecyclerviewTextItemBinding
import com.example.movieapp.recyclerview.items.TextItem

class TextViewHolder(
    private val binding: RecyclerviewTextItemBinding) : BaseViewHolder(binding.root) {
    private var item: TextItem? = null

    fun bind(item: TextItem) {
        this.item = item
        binding.text = item.text
        binding.textItem.setBackgroundColor(item.color)
        Log.d("INFO", "Bind TextViewHolder ${item.text}")
    }

}