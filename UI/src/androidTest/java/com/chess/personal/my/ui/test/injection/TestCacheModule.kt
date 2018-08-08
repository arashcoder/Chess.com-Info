package com.chess.personal.my.ui.test.injection

import android.app.Application
import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.data.repository.PlayersCache
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): PlayersDatabase {
        return PlayersDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun providePlayersCache(): PlayersCache {
        return mock()
    }

}