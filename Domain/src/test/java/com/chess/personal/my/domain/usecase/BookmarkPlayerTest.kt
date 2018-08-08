package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.BookmarkPlayer
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.test.PlayerDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkPlayerTest {

    private lateinit var bookmarkPlayer: BookmarkPlayer
    @Mock lateinit var playersRepository: PlayersRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkPlayer = BookmarkPlayer(playersRepository, postExecutionThread)
    }

    @Test
    fun bookmarkPlayerCompletes() {
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkPlayer.buildUseCaseCompletable(
                BookmarkPlayer.Params.forPlayer(PlayerDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkPlayer.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(playersRepository.bookmarkPlayer(any()))
                .thenReturn(completable)
    }

}