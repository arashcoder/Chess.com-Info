package com.chess.personal.my.ui.search

interface SearchResultListener {
    fun onClicked(searchResult: String)
    fun onBookmarked(name: String)
    fun onUnbookmarked(name: String)
}