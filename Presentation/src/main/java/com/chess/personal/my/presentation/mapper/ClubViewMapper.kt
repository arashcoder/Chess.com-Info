package com.chess.personal.my.presentation.mapper

import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.presentation.model.ClubView
import javax.inject.Inject

open class ClubViewMapper @Inject constructor() : Mapper<ClubView, Club> {

    override fun mapToView(type: Club): ClubView {
        return ClubView(type.name, type.avatar, type.dateCreated,
                type.lastActivity, type.visibility, type.description, type.countryUrl)
    }
}