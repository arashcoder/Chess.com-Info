package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.SingleUseCase
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetBookmarkedClubs @Inject constructor(
        private val clubRepository: ClubRepository,
        postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<String>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Nothing?): Single<List<String>> {
        return clubRepository.getBookmarkedClubs()
    }
}