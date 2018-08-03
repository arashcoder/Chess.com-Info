package com.chess.personal.my.remote.model

import com.squareup.moshi.Json

open class MonthlyGamesResponseModel{
    @field:Json(name = "games")
    var games: List<GameModel> = listOf()
}