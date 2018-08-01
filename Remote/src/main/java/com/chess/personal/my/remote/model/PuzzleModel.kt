package com.chess.personal.my.remote.model

import com.chess.personal.my.remote.converter.DateAdapter
import com.squareup.moshi.Json
import org.parceler.Parcel
import java.util.*

//@Parcel(Parcel.Serialization.BEAN)
open class PuzzleModel {

    @field:Json(name = "title")
    var title: String = ""

    @field:Json(name = "url")
    var url: String = ""

    @field:Json(name = "image")
    var imageUrl: String = ""

    @field:DateAdapter.MyDate
    @field:Json(name = "publish_time")
    var datePublished: Date = Date()
}