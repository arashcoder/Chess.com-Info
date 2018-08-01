package com.chess.personal.my.domain.repository

import com.chess.personal.my.domain.model.Puzzle
import io.reactivex.Single

interface PuzzleRepository {
    fun getDailyPuzzle(): Single<Puzzle>
    fun getRandomPuzzle(): Single<Puzzle>
}