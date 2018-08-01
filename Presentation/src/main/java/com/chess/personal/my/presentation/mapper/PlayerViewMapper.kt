package com.chess.personal.my.presentation.mapper

import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.presentation.model.PlayerView
import javax.inject.Inject

open class PlayerViewMapper @Inject constructor() : Mapper<PlayerView, Player> {

    override fun mapToView(type: Player): PlayerView {
        return PlayerView(type.username, type.name, type.profileUrl,
                type.title, type.status, type.avatar,
                type.location, type.country, type.dateJoined,
                type.dateLastOnline, type.followersCount, type.isBookmarked)
    }
}