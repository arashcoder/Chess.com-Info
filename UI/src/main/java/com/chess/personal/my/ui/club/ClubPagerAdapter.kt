package com.chess.personal.my.ui.club

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.Club

class ClubPagerAdapter(context: ClubActivity, fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    private val clubName: String = context.clubName

    companion object {
        val PROFILE_POS = 0
        val MEMBERS_POS = 1
    }

    private val titles: Array<String> = context.resources.getStringArray(R.array.player_tabs)

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            PROFILE_POS -> return ClubProfileFragment.newInstance()
            MEMBERS_POS -> return ClubMembersFragment.newInstance(clubName)
        }
        throw IllegalStateException("Position exceeded on view pager")
    }

}

