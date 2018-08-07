package com.chess.personal.my.ui.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.remote.converter.DateAdapter
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.Game
import kotlinx.android.synthetic.main.item_monthly_game.view.*
import java.text.SimpleDateFormat

class PlayerMonthlyGamesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var rootView: View = view

    companion object {

        fun inflate(parent: ViewGroup): PlayerMonthlyGamesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_monthly_game, parent, false)
            return PlayerMonthlyGamesViewHolder(view )
        }
    }


    fun bind(game: Game, username: String) {
        rootView.opponent_name.text = game.getOpponent(username)
        rootView.game_date.text = DateAdapter.format.format(game.endTime)
        rootView.result.text = game.getResult(username)
    }
}