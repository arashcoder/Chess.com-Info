package com.chess.personal.my.ui.injection.module

import android.app.Application
import com.chess.personal.my.cache.PlayersCacheImpl
import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.data.repository.PlayersCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): PlayersDatabase {
            return PlayersDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindPlayersCache(playersCache: PlayersCacheImpl): PlayersCache
}