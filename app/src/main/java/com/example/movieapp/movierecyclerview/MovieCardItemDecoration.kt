package com.example.movieapp.movierecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class MovieCardItemDecoration(
    verticalMargin: Int, horizontalMargin: Int
) : ItemDecoration() {
    val halfVerticalMargin = verticalMargin / 2
    val halfHorizontalMargin = horizontalMargin / 2

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.top = halfVerticalMargin
        outRect.bottom = halfVerticalMargin
        outRect.right = halfHorizontalMargin
        outRect.left = halfHorizontalMargin
    }
}