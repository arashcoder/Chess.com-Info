package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllGames @Inject constructor(private val playersRepository: PlayersRepository,
                                      postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<String>, GetAllGames.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<List<String>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.getAllGames(params.username)
    }

    data class Params constructor(val username: String) {
        companion object {
            fun forPlayer(username: String): Params {
                return Params(username)
            }
        }
    }
}