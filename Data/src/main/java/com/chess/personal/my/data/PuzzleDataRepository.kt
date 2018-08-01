package com.chess.personal.my.data

import com.chess.personal.my.data.mapper.PuzzleMapper
import com.chess.personal.my.data.store.PuzzleDataStoreFactory
import com.chess.personal.my.domain.model.Puzzle
import com.chess.personal.my.domain.repository.PuzzleRepository
import io.reactivex.Single
import javax.inject.Inject

class PuzzleDataRepository @Inject constructor(
        private val mapper: PuzzleMapper,
        private val factory: PuzzleDataStoreFactory)
    : PuzzleRepository {
    override fun getDailyPuzzle(): Single<Puzzle> {
        return factory.getDataStore().getDailyPuzzle().map { mapper.mapFromEntity(it) }
    }

    override fun getRandomPuzzle(): Single<Puzzle> {
        return factory.getDataStore().getRandomPuzzle().map { mapper.mapFromEntity(it) }
    }

}