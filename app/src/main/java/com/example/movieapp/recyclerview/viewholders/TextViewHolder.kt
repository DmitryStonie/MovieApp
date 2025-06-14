package com.example.movieapp.recyclerview.viewholders

import com.example.movieapp.databinding.RecyclerviewTextItemBinding
import com.example.movieapp.recyclerview.items.TextItem


class TextViewHolder(
    private val binding: RecyclerviewTextItemBinding
) : BaseViewHolder(binding.root) {

    fun bind(item: TextItem) {
        binding.item = item
    }
}