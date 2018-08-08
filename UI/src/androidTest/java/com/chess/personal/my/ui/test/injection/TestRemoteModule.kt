package com.chess.personal.my.ui.test.injection

import com.chess.personal.my.data.repository.PlayersRemote
import com.chess.personal.my.remote.service.ChessDotComService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): ChessDotComService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providePlayersRemote(): PlayersRemote {
        return mock()
    }

}