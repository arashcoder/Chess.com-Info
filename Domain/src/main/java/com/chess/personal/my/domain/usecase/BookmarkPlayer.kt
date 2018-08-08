package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Completable
import javax.inject.Inject

class BookmarkPlayer @Inject constructor(private val playersRepository: PlayersRepository,
                                          postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkPlayer.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.bookmarkPlayer(params.username)
    }

    data class Params constructor(val username: String) {
        companion object {
            fun forPlayer(username: String): Params {
                return Params(username)
            }
        }
    }

}