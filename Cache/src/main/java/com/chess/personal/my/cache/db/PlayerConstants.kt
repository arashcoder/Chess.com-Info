package com.chess.personal.my.cache.db

object PlayerConstants {

    const val TABLE_NAME = "players"

    const val COLUMN_USERNAME = "username"

    const val COLUMN_IS_BOOKMARKED = "is_bookmarked"

    const val QUERY_PLAYERS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_PLAYERS = "DELETE FROM $TABLE_NAME"

    const val QUERY_BOOKMARKED_PLAYERS = "SELECT * FROM $TABLE_NAME " +
            "WHERE $COLUMN_IS_BOOKMARKED = 1"

    const val QUERY_UPDATE_BOOKMARK_STATUS = "UPDATE $TABLE_NAME " +
            "SET $COLUMN_IS_BOOKMARKED = :isBookmarked WHERE " +
            "$COLUMN_USERNAME = :username"

}