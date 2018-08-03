package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.repository.PlayersDataStore
import com.chess.personal.my.data.repository.PlayersRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class PlayersRemoteDataStore @Inject constructor(
        private val playersRemote: PlayersRemote)
    : PlayersDataStore {
    override fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>> {
        return playersRemote.getMonthlyGames(username, year, month)
    }

    override fun getAllGames(username: String): Single<List<String>> {
        return playersRemote.getAllGames(username)
    }

    override fun getPlayer(username: String): Single<PlayerEntity> {
        return playersRemote.getPlayer(username)
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return playersRemote.getPlayers(countryISO)
    }

    override fun savePlayers(projects: List<String>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearPlayers(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

}