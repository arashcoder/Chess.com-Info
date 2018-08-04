package com.chess.personal.my.presentation.mapper

import com.chess.personal.my.domain.model.ClubMember
import com.chess.personal.my.presentation.model.ClubMemberView
import javax.inject.Inject

open class ClubMemberViewMapper @Inject constructor() : Mapper<ClubMemberView, ClubMember> {

    override fun mapToView(type: ClubMember): ClubMemberView {
        return ClubMemberView(type.username, type.dateJoined)
    }
}