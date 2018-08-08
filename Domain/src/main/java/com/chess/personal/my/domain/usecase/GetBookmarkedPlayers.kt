package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.SingleUseCase
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetBookmarkedPlayers @Inject constructor(
        private val playersRepository: PlayersRepository,
        postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<String>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Nothing?): Single<List<String>> {
        return playersRepository.getBookmarkedPlayers()
    }
}