package com.chess.personal.my.ui.club

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chess.personal.my.ui.R
import com.chess.personal.my.ui.model.ClubMember
import kotlinx.android.synthetic.main.item_club_member.view.*

class ClubMemberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var rootView: View = view

    companion object {

        fun inflate(parent: ViewGroup): ClubMemberViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_club_member, parent, false)
            return ClubMemberViewHolder(view)
        }
    }


    fun bind(member: ClubMember, color: Int) {
        rootView.member_name.text = member.username
        rootView.member_letter.letter = member.username?.substring(0, 1)
        rootView.member_letter.letterColor = Color.WHITE
        rootView.member_letter.shapeColor = color
    }
}