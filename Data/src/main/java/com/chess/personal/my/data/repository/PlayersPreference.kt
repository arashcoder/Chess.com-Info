package com.chess.personal.my.data.repository

import io.reactivex.Completable
import io.reactivex.Single

interface PlayersPreference  {
    fun getBookmarkedPlayers(): Single<List<String>>

    fun setPlayerAsBookmarked(username: String): Completable

    fun setPlayerAsNotBookmarked(username: String): Completable
}