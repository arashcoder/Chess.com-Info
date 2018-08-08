package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPlayer @Inject constructor(private val playersRepository: PlayersRepository,
                                    postExecutionThread: PostExecutionThread)
    : SingleUseCase<Player, GetPlayer.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<Player> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.getPlayer(params.username)
    }

    data class Params constructor(val username: String) {
        companion object {
            fun forPlayer(username: String): Params {
                return Params(username)
            }
        }
    }
}