package com.chess.personal.my.data

import com.chess.personal.my.data.mapper.PlayerMapper
import com.chess.personal.my.data.repository.PlayersCache
import com.chess.personal.my.data.repository.PlayersDataStore
import com.chess.personal.my.data.store.PlayersDataStoreFactory
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class PlayersDataRepository @Inject constructor(
        private val mapper: PlayerMapper,
        private val cache: PlayersCache,
        private val factory: PlayersDataStoreFactory)
    : PlayersRepository {


    override fun getPlayers(countryISO: String): Single<List<String>> {
//        return Single.zip(cache.arePlayersCached(),
//                cache.isPlayersCacheExpired(),
//                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
//                    Pair(areCached, isExpired)
//                })
//                .flatMap {
//                    factory.getDataStore(it.first, it.second).getPlayers(countryISO )
//                }
//                .flatMap { players ->
//                    factory.getCacheDataStore()
//                            .savePlayers(players)
//                            .andThen(Single.just(players))
//                }
////                .map {
////                    it.map {
////                        mapper.mapFromEntity(it)
////                    }
////                }
        return factory.getDataStore(false, true).getPlayers(countryISO )
    }

    override fun bookmarkPlayer(username: String): Completable {
       return factory.getPreferenceDataStore().setPlayerAsBookmarked(username)
        //return factory.getCacheDataStore().setPlayerAsBookmarked(username)
    }

    override fun unbookmarkPlayer(username: String): Completable {
        return factory.getPreferenceDataStore().setPlayerAsNotBookmarked(username)
        //return factory.getCacheDataStore().setPlayerAsNotBookmarked(username)
    }

    override fun getBookmarkedPlayers(): Single<List<String>> {
        return factory.getPreferenceDataStore().getBookmarkedPlayers()
        //return factory.getCacheDataStore().getBookmarkedPlayers()
//                .map {
//                    it.map { mapper.mapFromEntity(it) }
//                }
    }


}