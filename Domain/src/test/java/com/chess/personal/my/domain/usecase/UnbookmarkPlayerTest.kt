package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.UnbookmarkPlayer
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.test.PlayerDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkPlayerTest {

    private lateinit var unbookmarkPlayer: UnbookmarkPlayer
    @Mock
    lateinit var playersRepository: PlayersRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkPlayer = UnbookmarkPlayer(playersRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkPlayerCompletes() {
        stubUnbookmarkPlayer(Completable.complete())
        val testObserver = unbookmarkPlayer.buildUseCaseCompletable(
                UnbookmarkPlayer.Params.forPlayer(PlayerDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkPlayerThrowsException() {
        unbookmarkPlayer.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkPlayer(completable: Completable) {
        whenever(playersRepository.unbookmarkPlayer(any()))
                .thenReturn(completable)
    }
}