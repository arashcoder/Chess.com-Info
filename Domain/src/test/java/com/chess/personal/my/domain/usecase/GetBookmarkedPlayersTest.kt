package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.GetBookmarkedPlayers
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.test.PlayerDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedPlayersTest {

    private lateinit var getBookmarkedPlayers: GetBookmarkedPlayers
    @Mock
    lateinit var playersRepository: PlayersRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedPlayers = GetBookmarkedPlayers(playersRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedPlayersCompletes() {
        stubGetPlayers(Single.just(PlayerDataFactory.makePlayerList(2)))
        val testObserver = getBookmarkedPlayers.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedPlayersReturnsData() {
        val players = PlayerDataFactory.makePlayerList(2)
        stubGetPlayers(Single.just(players))
        val testObserver = getBookmarkedPlayers.buildUseCaseSingle().test()
        testObserver.assertValue(players)
    }

    private fun stubGetPlayers(single: Single<List<String>>) {
        whenever(playersRepository.getBookmarkedPlayers())
                .thenReturn(single)
    }

}