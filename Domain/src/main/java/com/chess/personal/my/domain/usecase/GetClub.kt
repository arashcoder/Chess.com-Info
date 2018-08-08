package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Single
import javax.inject.Inject

class GetClub @Inject constructor(private val playersRepository: ClubRepository,
                                    postExecutionThread: PostExecutionThread)
    : SingleUseCase<Club, GetClub.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<Club> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return playersRepository.getClub(params.clubName)
    }

    data class Params constructor(val clubName: String) {
        companion object {
            fun forClub(clubName: String): Params {
                return Params(clubName)
            }
        }
    }
}