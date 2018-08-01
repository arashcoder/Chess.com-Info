package com.chess.personal.my.ui.injection.module

import com.chess.personal.my.data.PlayersDataRepository
import com.chess.personal.my.data.PuzzleDataRepository
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.repository.PuzzleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: PlayersDataRepository): PlayersRepository

    @Binds
    abstract fun bindPuzzleRepository(dataRepository: PuzzleDataRepository): PuzzleRepository

}