package com.chess.personal.my.domain.usecase

import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.domain.usecase.SingleUseCase
import com.chess.personal.my.domain.model.Puzzle
import com.chess.personal.my.domain.repository.PuzzleRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDailyPuzzle @Inject constructor(private val puzzleRepository: PuzzleRepository,
                                         postExecutionThread: PostExecutionThread)
    : SingleUseCase<Puzzle, Nothing>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Nothing?): Single<Puzzle> {
        return puzzleRepository.getDailyPuzzle()
    }

}