package com.chess.personal.my.ui.injection.module

import com.chess.personal.my.data.PlayersDataRepository
import com.chess.personal.my.domain.repository.PlayersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: PlayersDataRepository): PlayersRepository
}