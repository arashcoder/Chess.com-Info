package com.chess.personal.my.ui.util

import android.app.Activity
import com.chess.personal.my.ui.player.PlayerActivity
import com.chess.personal.my.ui.search.BaseActivity
import com.chess.personal.my.ui.search.SearchActivity

object Navigator{
    fun navigateToPlayerProfile(activity: Activity, username: String) {
        activity.startActivity(PlayerActivity.newIntent(activity, username))
    }
    fun navigateToClubProfile(activity: Activity, clubName: String) {
        //activity.startActivity(ClubActivity.newIntent(activity, clubName))
    }
    fun navigateToSearch(activity: Activity, isPlayerSearch: Boolean) {
        activity.startActivity(SearchActivity.newIntent(activity, isPlayerSearch))
    }
    fun navigateToUrl(activity: Activity, url: String) {
        IntentUtil.openPage(activity as BaseActivity,  url)
    }
}