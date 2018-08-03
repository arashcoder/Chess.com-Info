package com.chess.personal.my.ui.player

interface PlayerAllGamesListener {
    fun onDownloadPgn(monthlyGameUrl: String)
    fun onClicked(monthlyGameUrl: String)
}