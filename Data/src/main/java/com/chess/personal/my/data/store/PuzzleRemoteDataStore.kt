package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.data.repository.PuzzleRemote
import io.reactivex.Single
import javax.inject.Inject

open class PuzzleRemoteDataStore @Inject constructor(
        private val puzzleRemote: PuzzleRemote)
    : PuzzleRemote {
    override fun getDailyPuzzle(): Single<PuzzleEntity> {
        return puzzleRemote.getDailyPuzzle()
    }

    override fun getRandomPuzzle(): Single<PuzzleEntity> {
        return puzzleRemote.getRandomPuzzle()
    }

}