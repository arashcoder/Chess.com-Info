package com.chess.personal.my.ui.mapper

import com.chess.personal.my.presentation.model.PlayerView
import com.chess.personal.my.ui.model.Player
import javax.inject.Inject

class PlayerViewMapper @Inject constructor(): ViewMapper<PlayerView, Player> {

    override fun mapToView(presentation: PlayerView): Player {
        return Player(presentation.username, presentation.name, presentation.profileUrl,
                presentation.title, presentation.status, presentation.avatar,
                presentation.location, presentation.country, presentation.dateJoined,
                presentation.dateLastOnline, presentation.followersCount, presentation.isBookmarked)
    }

}