package com.chess.personal.my.remote.mapper

import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.remote.model.PlayerModel
import javax.inject.Inject

class PlayersResponseModelMapper @Inject constructor(): ModelMapper<PlayerModel, PlayerEntity> {

    override fun mapFromModel(model: PlayerModel): PlayerEntity {
        return PlayerEntity(model.username, model.name, model.profileUrl, model.title,
                model.status, model.avatar, model.location, model.country,
                model.dateJoined, model.dateLastOnline, model.followersCount, false)
    }

}