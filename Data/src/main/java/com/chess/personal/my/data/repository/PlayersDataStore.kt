package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlayersDataStore {

    fun getPlayers(countryISO: String): Single<List<String>>

    fun savePlayers(players: List<String>): Completable

    fun clearPlayers(): Completable

    fun getBookmarkedPlayers(): Single<List<String>>

    fun setPlayerAsBookmarked(username: String): Completable

    fun setPlayerAsNotBookmarked(username: String): Completable

    fun getPlayer(username: String): Single<PlayerEntity>

    fun getAllGames(username: String): Single<List<String>>

    fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>>

}