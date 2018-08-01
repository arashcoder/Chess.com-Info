package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.PlayerEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlayersCache  {

    fun clearPlayers(): Completable

    fun savePlayers(players: List<String>): Completable

    fun getPlayers(countryISO: String): Single<List<String>>

    fun getBookmarkedPlayers(): Single<List<String>>

    fun setPlayerAsBookmarked(username: String): Completable

    fun setPlayerAsNotBookmarked(username: String): Completable

    fun arePlayersCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isPlayersCacheExpired(): Single<Boolean>

}