package com.chess.personal.my.ui.injection.module

import com.chess.personal.my.data.repository.ClubRemote
import com.chess.personal.my.data.repository.PlayersRemote
import com.chess.personal.my.data.repository.PuzzleRemote
import com.chess.personal.my.remote.ClubRemoteImpl
import com.chess.personal.my.remote.PlayersRemoteImpl
import com.chess.personal.my.remote.PuzzleRemoteImpl
import com.chess.personal.my.remote.service.ChessDotComService
import com.chess.personal.my.remote.service.ChessDotComServiceFactory
import com.chess.personal.my.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideChessDotComService(): ChessDotComService {
            return ChessDotComServiceFactory.makeChessDotComService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindPlayersRemote(playersRemote: PlayersRemoteImpl): PlayersRemote

    @Binds
    abstract fun bindClubRemote(clubRemote: ClubRemoteImpl): ClubRemote

    @Binds
    abstract fun bindPuzzleRemote(puzzleRemote: PuzzleRemoteImpl): PuzzleRemote

}