package com.chess.personal.my.remote.model

import com.chess.personal.my.remote.converter.DateAdapter
import com.squareup.moshi.Json
import java.util.*

class ClubMemberModel{
    @field:Json(name = "username")
    var username: String = ""

    @field:Json(name = "joined")
    @field:DateAdapter.MyDate
    var dateJoined: Date = Date()
}