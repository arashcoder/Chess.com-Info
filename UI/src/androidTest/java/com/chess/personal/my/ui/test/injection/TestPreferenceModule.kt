package com.chess.personal.my.ui.test.injection

import com.chess.personal.my.data.repository.PlayersPreference
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestPreferenceModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): PlayersPreference {
        return mock()
    }

}

