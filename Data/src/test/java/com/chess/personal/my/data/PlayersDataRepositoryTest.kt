package com.chess.personal.my.data

import com.chess.personal.my.data.mapper.GameMapper
import com.chess.personal.my.data.mapper.PlayerMapper
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.repository.PlayersCache
import com.chess.personal.my.data.repository.PlayersDataStore
import com.chess.personal.my.data.store.PlayersDataStoreFactory
import com.chess.personal.my.data.test.factory.DataFactory
import com.chess.personal.my.data.test.factory.PlayerFactory
import com.chess.personal.my.domain.model.Player
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlayersDataRepositoryTest {

    private val mapper = mock<PlayerMapper>()
    private val gameMapper = mock<GameMapper>()
    private val factory = mock<PlayersDataStoreFactory>()
    private val store = mock<PlayersDataStore>()
    private val cache = mock<PlayersCache>()
    private val repository = PlayersDataRepository(mapper=mapper, cache=cache, factory=factory, gameMapper=gameMapper)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubFactoryGetPreferenceDataStore()
        stubIsCacheExpired(Single.just(false))
        stubArePlayersCached(Single.just(false))
        stubSavePlayers(Completable.complete())
    }

    @Test
    fun getPlayersCompletes() {
        stubGetPlayers(Single.just(listOf(PlayerFactory.makePlayerEntity())))
        //stubMapper(PlayerFactory.makePlayer(), any())

        val testObserver = repository.getPlayers(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun getPlayersReturnsData() {
        val playerEntity = PlayerFactory.makePlayerEntity()
        //val player = PlayerFactory.makePlayer()
        stubGetPlayers(Single.just(listOf(playerEntity)))
        //stubMapper(player, playerEntity)

        val testObserver = repository.getPlayers(DataFactory.randomString()).test()
        testObserver.assertValue(listOf(playerEntity))
    }

    @Test
    fun getBookmarkedPlayersCompletes() {
        stubGetBookmarkedPlayers(Single.just(listOf(PlayerFactory.makePlayerEntity())))
        //stubMapper(PlayerFactory.makePlayer(), any())

        val testObserver = repository.getBookmarkedPlayers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedPlayersReturnsData() {
        val playerEntity = PlayerFactory.makePlayerEntity()
        //val player = PlayerFactory.makePlayer()
        stubGetBookmarkedPlayers(Single.just(listOf(playerEntity)))
        //stubMapper(player, playerEntity)

        val testObserver = repository.getBookmarkedPlayers().test()
        testObserver.assertValue(listOf(playerEntity))
    }

    @Test
    fun bookmarkPlayerCompletes() {
        stubBookmarkPlayer(Completable.complete())

        val testObserver = repository.bookmarkPlayer(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun unbookmarkPlayerCompletes() {
        stubUnBookmarkPlayer(Completable.complete())

        val testObserver = repository.unbookmarkPlayer(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    private fun stubBookmarkPlayer(completable: Completable) {
        whenever(store.setPlayerAsBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubUnBookmarkPlayer(completable: Completable) {
        whenever(store.setPlayerAsNotBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isPlayersCacheExpired())
                .thenReturn(single)
    }

    private fun stubArePlayersCached(single: Single<Boolean>) {
        whenever(cache.arePlayersCached())
                .thenReturn(single)
    }

    private fun stubMapper(model: Player, entity: PlayerEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubGetPlayers(observable: Single<List<String>>) {
        whenever(store.getPlayers(any()))
                .thenReturn(observable)
    }

    private fun stubGetBookmarkedPlayers(observable: Single<List<String>>) {
        whenever(store.getBookmarkedPlayers())
                .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubFactoryGetPreferenceDataStore() {
        whenever(factory.getPreferenceDataStore())
                .thenReturn(store)
    }

    private fun stubSavePlayers(completable: Completable) {
        whenever(store.savePlayers(any()))
                .thenReturn(completable)
    }

}