package com.chess.personal.my.ui.test.injection

import android.app.Application
import com.chess.personal.my.domain.repository.PlayersRepository
import com.chess.personal.my.ui.injection.module.PresentationModule
import com.chess.personal.my.ui.injection.module.UiModule
import com.chess.personal.my.ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        TestPreferenceModule::class,
        PresentationModule::class,
        UiModule::class,
        TestRemoteModule::class))
interface TestApplicationComponent {

    fun playersRepository(): PlayersRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}