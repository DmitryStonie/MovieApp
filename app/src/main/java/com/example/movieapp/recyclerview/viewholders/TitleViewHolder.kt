package com.example.movieapp.recyclerview.viewholders

import android.util.Log
import com.example.movieapp.databinding.RecyclerviewTitleItemBinding
import com.example.movieapp.recyclerview.items.TitleItem

class TitleViewHolder(private val binding: RecyclerviewTitleItemBinding): BaseViewHolder(binding.root) {
    fun bind(item: TitleItem){
        binding.title = item.title
        Log.d("INFO", "Bind TitleViewHolder")
    }
}