package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.PuzzleEntity
import io.reactivex.Single

interface PuzzleRemote {
    fun getDailyPuzzle(): Single<PuzzleEntity>
    fun getRandomPuzzle(): Single<PuzzleEntity>
}