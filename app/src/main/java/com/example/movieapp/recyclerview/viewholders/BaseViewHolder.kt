package com.example.movieapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

sealed class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(){}
}