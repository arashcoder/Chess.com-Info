package com.chess.personal.my.ui.test.injection

import com.chess.personal.my.domain.repository.ClubRepository
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.domain.repository.PuzzleRepository
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): PlayersRepository {
        return mock()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun providePuzzleRepository(): PuzzleRepository {
        return mock()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideClubRepository(): ClubRepository {
        return mock()
    }

}