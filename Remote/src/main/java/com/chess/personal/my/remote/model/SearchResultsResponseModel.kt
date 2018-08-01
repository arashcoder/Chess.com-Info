package com.chess.personal.my.remote.model

import com.squareup.moshi.Json

open class SearchResultsResponseModel{
    @field:Json(name = "players")
    var players: List<String> = listOf()

    @field:Json(name = "clubs")
    var clubs: List<String> = listOf()
}