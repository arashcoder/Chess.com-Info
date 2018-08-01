package com.chess.personal.my.ui.injection.module


import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.ui.UiThread
import com.chess.personal.my.ui.search.SearchActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): SearchActivity

    //@ContributesAndroidInjector
    //abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}