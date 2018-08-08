package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.model.ClubMember
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Single
import javax.inject.Inject

class GetClubMembers @Inject constructor(private val clubRepository: ClubRepository,
                                         postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<ClubMember>, GetClubMembers.Params>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Params?): Single<List<ClubMember>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return clubRepository.getClubMembers(params.clubName)
    }

    data class Params constructor(val clubName: String) {
        companion object {
            fun forGameMember(clubName: String): Params {
                return Params(clubName)
            }
        }
    }
}