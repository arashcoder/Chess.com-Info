package com.chess.personal.my.remote.model

import com.squareup.moshi.Json

open class AllGamesResponseModel{
    @field:Json(name = "archives")
    var archives: List<String> = listOf()
}