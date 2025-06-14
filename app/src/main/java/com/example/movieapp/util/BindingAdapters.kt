package com.example.movieapp.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "error", "placeholder"], requireAll = false)
    fun loadImage(view: ImageView, url: String?, error: Drawable, placeholder: Drawable) {
        if (url != null) {
            Picasso.get().load(url).error(error).placeholder(placeholder).into(view)
        } else {
            view.setImageDrawable(placeholder)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:color")
    fun setColor(view: View, color: Int) {
        view.setBackgroundResource(color)
    }

    @JvmStatic
    @BindingAdapter("bind:textCap")
    fun setColor(view: View, text: String) {
        (view as TextView).text = text.split(" ").joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
    }
}