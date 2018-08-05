package com.chess.personal.my.ui.mapper

import com.chess.personal.my.presentation.model.ClubMemberView
import com.chess.personal.my.ui.model.ClubMember
import javax.inject.Inject

class ClubMemberViewMapper @Inject constructor(): ViewMapper<ClubMemberView, ClubMember> {

    override fun mapToView(presentation: ClubMemberView): ClubMember {
        return ClubMember(presentation.username, presentation.dateJoined)
    }

}