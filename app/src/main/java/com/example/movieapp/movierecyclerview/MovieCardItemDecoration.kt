package com.example.movieapp.movierecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class MovieCardItemDecoration(
    verticalMargin: Int,
    horizontalMargin: Int,
    val itemCount: Int,
    val itemsOnRow: Int
) : ItemDecoration() {
    val halfVerticalMargin = verticalMargin / 2
    val halfHorizontalMargin = horizontalMargin / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        if (parent.getChildAdapterPosition(view) < itemsOnRow) {
            outRect.top = 0
        } else {
            outRect.top = halfVerticalMargin
        }
        if (itemCount - parent.getChildAdapterPosition(view) < itemsOnRow) {
            outRect.bottom = 0
        } else {
            outRect.bottom = halfVerticalMargin
        }
        if (layoutParams.spanIndex % 2 == 0) {
            outRect.right = halfHorizontalMargin
        } else {
            outRect.left = halfHorizontalMargin
        }
    }
}