package com.chess.personal.my.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.chess.personal.my.cache.db.PlayerConstants
import java.util.*

@Entity(tableName = PlayerConstants.TABLE_NAME)
data class CachedPlayer(
        @PrimaryKey
        @ColumnInfo(name = PlayerConstants.COLUMN_USERNAME)
        var username: String,
        var name: String,
        var profileUrl: String,
        var title: String,
        var status: String,
        var avatar: String,
        var location: String,
        var country: String,
        var dateJoined: String,
        var dateLastOnline: String,
        var followersCount: Int,
        @ColumnInfo(name = PlayerConstants.COLUMN_IS_BOOKMARKED)
        var isBookmarked: Boolean
)