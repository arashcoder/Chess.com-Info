package com.chess.personal.my.remote.model

import com.chess.personal.my.remote.converter.DateAdapter
import com.squareup.moshi.Json
import org.parceler.Parcel
import java.util.*

class GameModel {

    @field:Json(name = "url")
    var url: String = ""

    @field:Json(name = "fen")
    var fen: String = ""

    @field:Json(name = "pgn")
    var pgn: String = ""

    @field:Json(name = "start_time")
    @field:DateAdapter.MyDate
    var startTime: Date = Date()

    @field:Json(name = "end_time")
    @field:DateAdapter.MyDate
    var endTime: Date = Date()

    @field:Json(name = "time_control")
    var timeControl: String = ""

    @field:Json(name = "white")
    var white: GamePlayerModel = GamePlayerModel()

    @field:Json(name = "black")
    var black: GamePlayerModel = GamePlayerModel()

}


class GamePlayerModel {

    @field:Json(name = "username")
    var username: String = ""

    @field:Json(name = "rating")
    var rating: Int = 0

    @field:Json(name = "result")
    var result: String = ""

    @field:Json(name = "@id")
    var profileUrl: String = ""
}