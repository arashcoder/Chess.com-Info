package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import io.reactivex.Single

interface PlayersRemote {
    fun getPlayers(countryISO: String): Single<List<String>>
    fun getPlayer(username: String): Single<PlayerEntity>
    fun getAllGames(username: String): Single<List<String>>
    fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>>
}