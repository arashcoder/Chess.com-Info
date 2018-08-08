package com.chess.personal.my.ui.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.ArrayList
import javax.inject.Inject

class PlayerAllGamesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    var listener: PlayerAllGamesListener? = null
    var values: ArrayList<String> = ArrayList()
    private val colors: IntArray by lazy {context.resources.getIntArray(R.array.cool_colors)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = PlayerAllGamesViewHolder.inflate(parent)
        holder.itemView.download_pgn.setOnClickListener {
            val position = holder.adapterPosition
            listener?.onDownloadPgn(getEntry(position))
        }
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            listener?.onClicked(getEntry(position))
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerAllGamesViewHolder) {
            holder.itemView.setTag(R.id.list_position, position)
            holder.bind(getEntry(position), colors[position % colors.size])
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun getEntry(position: Int): String {
        return values[position]
    }

}