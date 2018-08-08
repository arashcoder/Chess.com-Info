package com.chess.personal.my.ui.club

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.ClubMember
import javax.inject.Inject

class ClubMembersAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    var listener: ClubMembersListener? = null
    var values: ArrayList<ClubMember> = ArrayList()
    private val colors: IntArray by lazy {context.resources.getIntArray(R.array.cool_colors)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = ClubMemberViewHolder.inflate(parent)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            listener?.onClicked(getEntry(position))
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ClubMemberViewHolder) {
            holder.itemView.setTag(R.id.list_position, position)
            holder.bind(getEntry(position), colors[position % colors.size])
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun getEntry(position: Int): ClubMember {
        return values[position]
    }
}