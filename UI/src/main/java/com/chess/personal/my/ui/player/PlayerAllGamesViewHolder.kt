package com.chess.personal.my.ui.player

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.SimpleDateFormat

class PlayerAllGamesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var rootView: View = view

    companion object {

        fun inflate(parent: ViewGroup): PlayerAllGamesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_game, parent, false)
            return PlayerAllGamesViewHolder(view)
        }
    }


    fun bind(date: String, color: Int) {
        val parts = date.split('/')
        val year = parts[parts.size-2]
        val month = parts[parts.size-1]
        var parsedDate = SimpleDateFormat("yyyy/MM").parse("$year/$month")
        val formattedDate = SimpleDateFormat("MMM yyyy").format(parsedDate)
        rootView.date.text = formattedDate
        rootView.date_letter.letter = formattedDate.substring(0, 3)
        rootView.date_letter.letterColor = Color.WHITE
        rootView.date_letter.shapeColor = color
    }
}