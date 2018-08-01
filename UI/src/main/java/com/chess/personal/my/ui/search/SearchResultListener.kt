package com.chess.personal.my.ui.search

interface SearchResultListener {

    fun onBookmarkedItemClicked(username: String)

    fun onItemClicked(username: String)

}