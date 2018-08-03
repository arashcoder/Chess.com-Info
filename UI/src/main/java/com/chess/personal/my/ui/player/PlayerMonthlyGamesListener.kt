package com.chess.personal.my.ui.player

import com.chess.personal.my.ui.model.Game

interface PlayerMonthlyGamesListener {
    fun onClicked(game: Game)
}