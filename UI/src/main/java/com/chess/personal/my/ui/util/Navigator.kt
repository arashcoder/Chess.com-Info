package com.chess.personal.my.ui.util

import android.app.Activity
import com.chess.personal.my.ui.club.ClubActivity
import com.chess.personal.my.ui.club.ClubHomeActivity
import com.chess.personal.my.ui.player.PlayerActivity
import com.chess.personal.my.ui.player.PlayerHomeActivity
import com.chess.personal.my.ui.search.BaseActivity
import com.chess.personal.my.ui.search.HomeActivity
import com.chess.personal.my.ui.search.SearchActivity

object Navigator{
    fun navigateToHome(activity: Activity) {
        activity.startActivity(HomeActivity.newIntent(activity))
    }
    fun navigateToPlayerHome(activity: Activity) {
        activity.startActivity(PlayerHomeActivity.newIntent(activity))
    }
    fun navigateToClubHome(activity: Activity) {
        activity.startActivity(ClubHomeActivity.newIntent(activity))
    }
    fun navigateToPlayerProfile(activity: Activity, username: String) {
        activity.startActivity(PlayerActivity.newIntent(activity, username))
    }
    fun navigateToClubProfile(activity: Activity, clubName: String) {
        activity.startActivity(ClubActivity.newIntent(activity, clubName))
    }
    fun navigateToSearch(activity: Activity, isPlayerSearch: Boolean) {
        activity.startActivity(SearchActivity.newIntent(activity, isPlayerSearch))
    }
    fun navigateToUrl(activity: Activity, url: String) {
        IntentUtil.openPage(activity as BaseActivity,  url)
    }
}