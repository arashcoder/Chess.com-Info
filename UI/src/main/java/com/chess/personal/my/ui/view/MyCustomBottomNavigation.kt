package com.chess.personal.my.ui.view

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.club.ClubHomeActivity
import com.chess.personal.my.ui.player.PlayerHomeActivity
import com.chess.personal.my.ui.search.HomeActivity
import com.chess.personal.my.ui.util.Navigator


class MyCustomBottomNavigation : BottomNavigationView{

    private val navigationSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                if (context !is HomeActivity) {
                    Navigator.navigateToHome(context as Activity)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_players -> {
                if (context !is PlayerHomeActivity) {
                    Navigator.navigateToPlayerHome(context as Activity)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_clubs -> {
                if (context !is ClubHomeActivity) {
                    Navigator.navigateToClubHome(context as Activity)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        inflateMenu(R.menu.main_navigation)

        itemBackgroundResource = R.color.main_blue
        itemIconTintList = ColorStateList.valueOf(resources.getColor(R.color.yellow))
        itemTextColor = ColorStateList.valueOf(resources.getColor(R.color.white))

        setSelectedNavigationItem()
        setOnNavigationItemSelectedListener(navigationSelectedListener)
    }

    private fun setSelectedNavigationItem() {
//        val menu = menu
//        var selectedItemId = 0
//
//        when (context) {
//            is HomeActivity -> selectedItemId = R.id.action_home
//            is PlayerHomeActivity -> selectedItemId = R.id.action_players
//            is ClubHomeActivity -> selectedItemId = R.id.action_clubs
//        }
//
//        var i = 0
//        val size = menu.size()
//        while (i < size) {
//            val item = menu.getItem(i)
//            item.isChecked = item.itemId == selectedItemId
//            i++
//        }
        when (context) {
            is HomeActivity -> selectedItemId = R.id.action_home
            is PlayerHomeActivity -> selectedItemId = R.id.action_players
            is ClubHomeActivity -> selectedItemId = R.id.action_clubs
        }
    }
}