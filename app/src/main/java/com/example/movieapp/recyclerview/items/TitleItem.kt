package com.example.movieapp.recyclerview.items

class TitleItem(val title: String): BaseItem {
    override val type: Int
        get() = BaseItem.Type.TitleItem.value
}