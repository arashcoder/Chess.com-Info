package com.chess.personal.my.ui.fragment

import android.support.v4.app.Fragment
import com.chess.personal.my.ui.search.BaseActivity

abstract class BaseFragment : Fragment() {

    val baseActivity by lazy {
        activity as BaseActivity
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}