package com.example.movieapp.recyclerview.items

data class TitleItem(val title: String) : BaseItem {
    override val type: Int
        get() = BaseItem.Type.TitleItem.value
}