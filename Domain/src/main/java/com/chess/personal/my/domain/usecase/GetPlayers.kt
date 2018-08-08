package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.repository.PlayersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPlayers @Inject constructor(private val playersRepository: PlayersRepository,
                                      postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<String>, GetPlayers.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<List<String>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.getPlayers(params.countryISO)
    }

    data class Params constructor(val countryISO: String) {
        companion object {
            fun forPlayer(countryISO: String): Params {
                return Params(countryISO)
            }
        }
    }
}