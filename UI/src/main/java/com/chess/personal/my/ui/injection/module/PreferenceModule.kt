package com.chess.personal.my.ui.injection.module

import android.app.Application
import com.chess.personal.my.cache.PlayersCacheImpl
import com.chess.personal.my.cache.db.PlayersDatabase
import com.chess.personal.my.data.repository.ClubPreference
import com.chess.personal.my.data.repository.PlayersCache
import com.chess.personal.my.data.repository.PlayersPreference
import com.chess.personal.my.preference.ClubPreferenceImpl
import com.chess.personal.my.preference.PlayersPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PreferenceModule {

    @Binds
    abstract fun bindPlayersPreference(playersPrefs: PlayersPreferenceImpl): PlayersPreference

    @Binds
    abstract fun bindClubPreference(playersPrefs: ClubPreferenceImpl): ClubPreference
}