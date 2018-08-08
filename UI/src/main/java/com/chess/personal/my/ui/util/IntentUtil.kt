package com.chess.personal.my.ui.util

import android.app.Activity
import android.content.res.Resources
import android.net.Uri
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.search.BaseActivity
import com.novoda.simplechromecustomtabs.SimpleChromeCustomTabs
import com.novoda.simplechromecustomtabs.navigation.IntentCustomizer
import com.novoda.simplechromecustomtabs.navigation.SimpleChromeCustomTabsIntentBuilder
import java.lang.ref.WeakReference

object IntentUtil {

    fun openPage(activity: BaseActivity, url: String) {
        val primaryColor = R.color.main_blue
        SimpleChromeCustomTabs.getInstance()
                .withFallback(BrowserFallback(activity))
                .withIntentCustomizer(MyIntentCustomizer(activity, primaryColor))
                .navigateTo(Uri.parse(url), activity)
    }
}

class MyIntentCustomizer(activity: Activity, private val colorToolbar: Int) : IntentCustomizer {

    private val activity: WeakReference<Activity> = WeakReference(activity)

    override fun onCustomiseIntent(simpleChromeCustomTabsIntentBuilder: SimpleChromeCustomTabsIntentBuilder): SimpleChromeCustomTabsIntentBuilder {
        val activity = this.activity.get() ?: return simpleChromeCustomTabsIntentBuilder
        return simpleChromeCustomTabsIntentBuilder
                .withStartAnimations(activity, R.anim.fade_in, R.anim.do_nothing)
                .withExitAnimations(activity, R.anim.do_nothing, R.anim.fade_out)
                .withToolbarColor(colorToolbar)
    }
}