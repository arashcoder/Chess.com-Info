package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.repository.PlayersCache
import com.chess.personal.my.data.repository.PlayersDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class PlayersCacheDataStore @Inject constructor(
        private val playersCache: PlayersCache)
    : PlayersDataStore {
    override fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllGames(username: String): Single<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayer(username: String): Single<PlayerEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return playersCache.getPlayers(countryISO)
    }

    override fun savePlayers(players: List<String>): Completable {
        return playersCache.savePlayers(players)
                .andThen(playersCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearPlayers(): Completable {
        return playersCache.clearPlayers()
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        return playersCache.getBookmarkedPlayers()
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        return playersCache.setPlayerAsBookmarked(username)
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        return playersCache.setPlayerAsNotBookmarked(username)
    }

}