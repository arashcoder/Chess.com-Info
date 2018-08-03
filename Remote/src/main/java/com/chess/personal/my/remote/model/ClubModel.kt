package com.chess.personal.my.remote.model

import com.chess.personal.my.remote.converter.DateAdapter
import com.squareup.moshi.Json
import java.util.*

open class ClubModel {

    @field:Json(name = "name")
    var name: String = ""

    @field:Json(name = "icon")
    var avatar: String? = ""

    @field:Json(name = "created")
    @field:DateAdapter.MyDate
    var created: Date = Date()

    @field:Json(name = "last_activity")
    @field:DateAdapter.MyDate
    var lastActivity: Date = Date()

    @field:Json(name = "visibility")
    var visibility: String = ""

    @field:Json(name = "description")
    var description: String = ""

    @field:Json(name = "country")
    var countryUrl: String = ""

}