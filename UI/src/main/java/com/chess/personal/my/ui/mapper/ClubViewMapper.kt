package com.chess.personal.my.ui.mapper

import com.chess.personal.my.presentation.model.ClubView
import com.chess.personal.my.ui.model.Club
import javax.inject.Inject

class ClubViewMapper @Inject constructor(): ViewMapper<ClubView, Club> {

    override fun mapToView(presentation: ClubView): Club {
        return Club(presentation.name, presentation.avatar, presentation.dateCreated,
                presentation.lastActivity, presentation.visibility, presentation.description,
                presentation.countryUrl)
    }

}