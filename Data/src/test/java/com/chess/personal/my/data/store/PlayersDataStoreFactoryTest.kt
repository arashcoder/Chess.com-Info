package com.chess.personal.my.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class PlayersDataStoreFactoryTest {

    private val cacheStore = mock<PlayersCacheDataStore>()
    private val remoteStore = mock<PlayersRemoteDataStore>()
    private val prefStore = mock<PlayersPreferenceDataStore>()
    private val factory = PlayersDataStoreFactory(cacheStore, remoteStore, prefStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenPlayersNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}