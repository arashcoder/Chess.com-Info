package com.chess.personal.my.ui.util

import android.app.Activity
import com.chess.personal.my.ui.search.BaseActivity

object Navigator{
    fun navigateToUrl(activity: Activity, url: String) {
        IntentUtil.openPage(activity as BaseActivity,  url)
    }
}