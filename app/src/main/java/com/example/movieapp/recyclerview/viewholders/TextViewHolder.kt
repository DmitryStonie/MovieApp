package com.example.movieapp.recyclerview.viewholders

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.movieapp.databinding.RecyclerviewTextItemBinding
import com.example.movieapp.recyclerview.items.TextItem


class TextViewHolder(
    private val binding: RecyclerviewTextItemBinding) : BaseViewHolder(binding.root) {

    fun bind(item: TextItem) {
        binding.item = item

    }
    companion object{
        @JvmStatic
        @BindingAdapter("bind:color")
        fun setColor(view: View, color: Int) {
            view.setBackgroundResource(color)
        }
    }

}