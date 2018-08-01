package com.chess.personal.my.data.store

import com.chess.personal.my.data.repository.PlayersDataStore
import javax.inject.Inject

open class PlayersDataStoreFactory @Inject constructor(
        private val playersCacheDataStore: PlayersCacheDataStore,
        private val playersRemoteDataStore: PlayersRemoteDataStore,
        private val playersPrefDataStore: PlayersPreferenceDataStore) {

    open fun getDataStore(playersCached: Boolean,
                          cacheExpired: Boolean): PlayersDataStore {
        return if (playersCached && !cacheExpired) {
            playersCacheDataStore
        } else {
            playersRemoteDataStore
        }
    }

    open fun getCacheDataStore(): PlayersDataStore {
        return playersCacheDataStore
    }

    fun getRemoteDataStore(): PlayersDataStore {
        return playersRemoteDataStore
    }

    open fun getPreferenceDataStore(): PlayersDataStore{
        return playersPrefDataStore
    }

}