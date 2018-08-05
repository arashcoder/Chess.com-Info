package com.chess.personal.my.ui.injection.module


import com.chess.personal.my.domain.executer.PostExecutionThread
import com.chess.personal.my.ui.UiThread
import com.chess.personal.my.ui.club.ClubActivity
import com.chess.personal.my.ui.club.ClubHomeActivity
import com.chess.personal.my.ui.club.ClubMembersFragment
import com.chess.personal.my.ui.player.PlayerActivity
import com.chess.personal.my.ui.player.PlayerAllGamesFragment
import com.chess.personal.my.ui.player.PlayerHomeActivity
import com.chess.personal.my.ui.player.PlayerMonthlyGamesFragment
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
    abstract fun contributesPlayerHomeActivity(): PlayerHomeActivity

    @ContributesAndroidInjector
    abstract fun contributesClubHomeActivity(): ClubHomeActivity

    @ContributesAndroidInjector
    abstract fun contributesClubActivity(): ClubActivity

    @ContributesAndroidInjector
    abstract fun contributesPlayerAllGamesFragment(): PlayerAllGamesFragment

    @ContributesAndroidInjector
    abstract fun contributesPlayerMonthlyGamesFragment(): PlayerMonthlyGamesFragment

    @ContributesAndroidInjector
    abstract fun contributesClubMembersFragment(): ClubMembersFragment

}