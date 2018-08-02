package com.chess.personal.my.ui.player

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.Player

class PlayerPagerAdapter(context: PlayerActivity, fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    private val mFragmentManager: FragmentManager? = null
    var gameFragment: Fragment? = null

//    private inner class GamePageListener: GamesListFragmentListener{
//        override fun onGoToNextFragment(year: String, month: String) {
//            mFragmentManager?.beginTransaction()?.remove(gameFragment)
//                    ?.commitNow()
//            if (gameFragment is PlayerAllGamesListFragment){
//                gameFragment = PlayerMonthlyGamesFragment.newInstance(player.username, month, year, GamePageListener())
//            }else{
//                gameFragment = PlayerAllGamesListFragment.newInstance(player.username, GamePageListener())
//            }
//            notifyDataSetChanged()
//        }
//    }


    companion object {
        val PROFILE_POS = 0
        val GAMES_POS = 1
    }

    private val player: Player = context.player
    private val titles: Array<String> = context.resources.getStringArray(R.array.player_tabs)

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            PROFILE_POS -> return PlayerProfileFragment.newInstance()
        //STATS_POS -> return PlayerProfileFragment.newInstance()
            GAMES_POS -> {
//                if (gameFragment == null) {
//                    gameFragment = PlayerAllGamesListFragment.newInstance(player.username, GamePageListener())
//                }
//                return gameFragment
                return PlayerProfileFragment.newInstance()
            }
        }
        throw IllegalStateException("Position exceeded on view pager")
    }


//    override fun getItemPosition(`object`: Any): Int {
//        if (`object` is PlayerAllGamesListFragment && gameFragment is PlayerMonthlyGamesFragment) {
//            return PagerAdapter.POSITION_NONE
//        }
//        return if (`object` is PlayerMonthlyGamesFragment && gameFragment is PlayerAllGamesListFragment) {
//            PagerAdapter.POSITION_NONE
//        } else PagerAdapter.POSITION_UNCHANGED
//    }

}

interface GamesListFragmentListener {
    fun onGoToNextFragment(year: String, month: String)
}