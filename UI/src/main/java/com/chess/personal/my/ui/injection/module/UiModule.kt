package com.chess.personal.my.ui.injection.module


import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.ui.UiThread
import com.chess.personal.my.ui.player.PlayerActivity
import com.chess.personal.my.ui.player.PlayerAllGamesFragment
import com.chess.personal.my.ui.search.HomeActivity
import com.chess.personal.my.ui.search.SearchActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributesSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun contributesPlayerProfileActivity(): PlayerActivity

    @ContributesAndroidInjector
    abstract fun contributesPlayerAllGamesFragment(): PlayerAllGamesFragment

}