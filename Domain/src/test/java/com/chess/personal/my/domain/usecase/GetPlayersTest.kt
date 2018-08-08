package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.GetPlayers
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.test.PlayerDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPlayersTest {

    private lateinit var getPlayers: GetPlayers
    @Mock
    lateinit var playersRepository: PlayersRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPlayers = GetPlayers(playersRepository, postExecutionThread)
    }

    @Test
    fun getPlayersCompletes() {
        stubGetPlayers(Single.just(PlayerDataFactory.makePlayerList(2)))
        val testObserver = getPlayers.buildUseCaseSingle(
                GetPlayers.Params.forPlayer(PlayerDataFactory.randomUuid()))
                .test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun getPlayersThrowsException() {
        getPlayers.buildUseCaseSingle().test()
    }

    @Test
    fun getPlayersReturnsData() {
        val players = PlayerDataFactory.makePlayerList(2)
        stubGetPlayers(Single.just(players))
        val testObserver = getPlayers.buildUseCaseSingle(
                GetPlayers.Params.forPlayer(PlayerDataFactory.randomUuid()))
                .test()
        testObserver.assertValue(players)
    }

    private fun stubGetPlayers(single: Single<List<String>>) {
        whenever(playersRepository.getPlayers(any()))
                .thenReturn(single)
    }

}