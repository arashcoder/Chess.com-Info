package com.chess.personal.my.remote.model

import com.chess.personal.my.remote.converter.DateAdapter
import com.squareup.moshi.Json
import org.parceler.Parcel
import java.util.*

//@Parcel(Parcel.Serialization.BEAN)
open class PlayerModel {

    @field:Json(name = "url")
    var profileUrl: String = ""

    @field:Json(name = "username")
    var username: String = ""

    @field:Json(name = "title")
    var title: String? = ""

    @field:Json(name = "status")
    var status: String = ""

    @field:Json(name = "name")
    var name: String? = ""

    @field:Json(name = "avatar")
    var avatar: String? = ""

    @field:Json(name = "location")
    var location: String? = ""

    @field:Json(name = "country")
    var country: String = ""

    @field:DateAdapter.MyDate
    @field:Json(name = "joined")
    var dateJoined: Date = Date()

    @field:DateAdapter.MyDate
    @field:Json(name = "last_online")
    var dateLastOnline: Date = Date()

    @field:Json(name = "followers")
    var followersCount: Int = 0
}