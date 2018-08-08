package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.model.Game
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMonthlyGames @Inject constructor(private val playersRepository: PlayersRepository,
                                      postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<Game>, GetMonthlyGames.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<List<Game>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.getMonthlyGames(params.username, params.year, params.month)
    }

    data class Params constructor(val username: String, val year: String, val month: String) {
        companion object {
            fun forGame(username: String, year: String, month: String): Params {
                return Params(username, year, month)
            }
        }
    }
}