package com.chess.personal.my.remote

import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.data.repository.PuzzleRemote
import com.chess.personal.my.remote.mapper.PuzzleResponseModelMapper
import com.chess.personal.my.remote.service.ChessDotComService
import io.reactivex.Single
import javax.inject.Inject

class PuzzleRemoteImpl @Inject constructor(
        private val service: ChessDotComService,
        private val mapper: PuzzleResponseModelMapper)
    : PuzzleRemote {
    override fun getRandomPuzzle(): Single<PuzzleEntity> {
        return service.getRandomPuzzle()
                .map {
                    mapper.mapFromModel(it)
                }
    }

    override fun getDailyPuzzle(): Single<PuzzleEntity> {
        return service.getDailyPuzzle()
                .map {
                    mapper.mapFromModel(it)
                }
    }
}

