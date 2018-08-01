package com.chess.personal.my.ui.injection.module

import android.app.Application
import com.chess.personal.my.cache.PlayersCacheImpl
import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.data.repository.PlayersCache
import com.chess.personal.my.data.repository.PlayersPreference
import com.chess.personal.my.preference.PlayersPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PreferenceModule {

//    @Module
//    companion object {
//        @Provides
//        @JvmStatic
//        fun providesDataBase(application: Application): PlayersDatabase {
//            return PlayersDatabase.getInstance(application)
//        }
//    }

    @Binds
    abstract fun bindPplayersCache(playersPrefs: PlayersPreferenceImpl): PlayersPreference
}