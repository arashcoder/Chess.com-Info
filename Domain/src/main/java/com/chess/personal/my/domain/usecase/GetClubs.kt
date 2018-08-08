package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Single
import javax.inject.Inject

class GetClubs @Inject constructor(private val clubRepository: ClubRepository,
                                   postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<String>, GetClubs.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<List<String>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return clubRepository.getClubs(params.countryISO)
    }

    data class Params constructor(val countryISO: String) {
        companion object {
            fun forClub(countryISO: String): Params {
                return Params(countryISO)
            }
        }
    }
}