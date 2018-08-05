package com.chess.personal.my.ui.club

import com.chess.personal.my.ui.model.ClubMember

interface ClubMembersListener {
    fun onClicked(member: ClubMember)
}