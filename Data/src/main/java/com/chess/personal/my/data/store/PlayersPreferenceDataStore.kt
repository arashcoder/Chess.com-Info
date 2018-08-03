package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.repository.PlayersDataStore
import com.chess.personal.my.data.repository.PlayersPreference
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class PlayersPreferenceDataStore @Inject constructor(
        private val playersPreference: PlayersPreference)
    : PlayersDataStore {
    override fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllGames(username: String): Single<List<String>> {
        throw UnsupportedOperationException("Getting all games isn't supported here...")
    }

    override fun getPlayer(username: String): Single<PlayerEntity> {
        throw UnsupportedOperationException("Getting player isn't supported here...")
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        throw UnsupportedOperationException("Getting players isn't supported here...")
    }

    override fun savePlayers(players: List<String>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearPlayers(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        return playersPreference.getBookmarkedPlayers()
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        return playersPreference.setPlayerAsBookmarked(username)
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        return playersPreference.setPlayerAsNotBookmarked(username)
    }

}