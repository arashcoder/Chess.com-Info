package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Completable
import javax.inject.Inject

class BookmarkClub @Inject constructor(private val clubRepository: ClubRepository,
                                       postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkClub.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return clubRepository.bookmarkClub(params.clubName)
    }

    data class Params constructor(val clubName: String) {
        companion object {
            fun forClub(clubName: String): Params {
                return Params(clubName)
            }
        }
    }

}