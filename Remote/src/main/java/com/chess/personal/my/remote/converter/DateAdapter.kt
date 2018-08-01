package com.chess.personal.my.remote.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter {

    companion object {
        val format by lazy {
            SimpleDateFormat("MMM dd, yyyy")
        }
    }

    @Retention(AnnotationRetention.RUNTIME)
    @JsonQualifier
    annotation class MyDate

    @ToJson
    fun toJson(@MyDate date: Date): String {
        return format.format(date)
    }

    @FromJson
    @MyDate
    fun fromJson(timestamp: Long): Date {
        return Date(timestamp*1000L)
    }
}