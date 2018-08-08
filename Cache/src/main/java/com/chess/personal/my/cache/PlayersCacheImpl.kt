package com.chess.personal.my.cache

import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.cache.model.Config
import com.chess.personal.my.data.repository.PlayersCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PlayersCacheImpl @Inject constructor(
        private val playersDatabase: PlayersDatabase)
        //private val mapper: CachedPla)
    : PlayersCache {

    override fun clearPlayers(): Completable {
        return Completable.complete()
    }

    override fun savePlayers(players: List<String>): Completable {
        return Completable.complete()
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return Single.just(listOf())
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        return Single.just(listOf())
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        return Completable.complete()
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        return Completable.complete()
    }

    override fun arePlayersCached(): Single<Boolean> {
        return Single.just(true)
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            playersDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isPlayersCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return playersDatabase.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }

}