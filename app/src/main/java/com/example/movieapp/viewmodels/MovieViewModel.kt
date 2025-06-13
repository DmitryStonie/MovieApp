package com.example.movieapp.viewmodels

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.DataRepository
import com.squareup.picasso.Picasso
import java.util.Locale

class MovieViewModel(val dataRepository: DataRepository) : ViewModel() {
    private var movie: Movie? = null
    fun getSelectedMovie() {
        val result = dataRepository.getSelectedMovie()
        if (result != null) {
            movie = result
        }
    }

    val name: String
        get() = movie?.name?.split(" ")?.joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
            ?: ""
    val localizedName: String get() = movie?.localizedName ?: ""
    val rating: String?
        get() = if (movie?.rating != null) {
            String.format(Locale.UK, "%1$,.1f", movie?.rating)
        } else {
            "-"
        }
    val description: String? get() = movie?.description ?: ""
    val movieInfo: String?
        get() = "${movie?.genres?.joinToString(", ")}${if (movie?.genres == null || movie?.genres?.size == 0) "" else ", "}${movie?.year}"
    val imageUrl: String? get() = movie?.imageUrl

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl", "error", "placeholder"], requireAll = false)
        fun loadImage(view: ImageView, url: String?, error: Drawable, placeholder: Drawable) {
            if (url != null) {
                Picasso.get().load(url).error(error).placeholder(placeholder).into(view)
            } else{
                view.setImageDrawable(placeholder)
            }
        }
    }
}