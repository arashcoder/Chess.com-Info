package com.chess.personal.my.ui.search

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import kotlinx.android.synthetic.main.item_search.view.*

class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var rootView: View = view


    companion object {

        fun inflate(parent: ViewGroup): SearchResultViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
            return SearchResultViewHolder(view)
        }
    }


    fun bind(searchResult: String, color: Int, favorites: List<String>) {
        rootView.item_name.text = searchResult
        rootView.item_letter.letter = searchResult.substring(0, 1)
        rootView.item_letter.letterColor = Color.WHITE
        rootView.item_letter.shapeColor = color
        rootView.btn_fav.isLiked = favorites.any { it == searchResult }
    }
}