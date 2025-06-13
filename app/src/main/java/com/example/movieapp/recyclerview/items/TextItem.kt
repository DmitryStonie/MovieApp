package com.example.movieapp.recyclerview.items

class TextItem(val text: String, val color: Int): BaseItem {
    override val type: Int
        get() = BaseItem.Type.TextItem.value
}