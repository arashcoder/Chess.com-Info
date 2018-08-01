package com.chess.personal.my.data.store

import com.chess.personal.my.data.repository.PuzzleRemote
import javax.inject.Inject

open class PuzzleDataStoreFactory @Inject constructor(
        private val puzzleRemoteDataStore: PuzzleRemoteDataStore) {

    open fun getDataStore(): PuzzleRemote {
        return puzzleRemoteDataStore
    }

}
