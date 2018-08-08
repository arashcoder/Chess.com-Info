package com.chess.personal.my.ui.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.ArrayList
import javax.inject.Inject

class PlayerMonthlyGamesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    var listener: PlayerMonthlyGamesListener? = null
    var values: ArrayList<Game> = ArrayList()
    var username: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = PlayerMonthlyGamesViewHolder.inflate(parent)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            listener?.onClicked(getEntry(position))
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerMonthlyGamesViewHolder) {
            holder.itemView.setTag(R.id.list_position, position)
            holder.bind(getEntry(position), username)
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun getEntry(position: Int): Game {
        return values[position]
    }


}