package com.chess.personal.my.domain.repository

import com.chess.personal.my.domain.model.Player
import io.reactivex.Completable
import io.reactivex.Single

interface PlayersRepository {
    fun getPlayers(countryISO: String): Single<List<String>>
    fun bookmarkPlayer(username: String): Completable
    fun unbookmarkPlayer(username: String): Completable
    fun getBookmarkedPlayers(): Single<List<String>>
}