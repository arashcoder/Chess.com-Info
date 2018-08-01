package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.domain.model.Player
import javax.inject.Inject

open class PlayerMapper @Inject constructor() : EntityMapper<PlayerEntity, Player> {

    override fun mapFromEntity(entity: PlayerEntity): Player {
        return Player(entity.username, entity.name, entity.profileUrl,
                entity.title, entity.status, entity.avatar,
                entity.location, entity.country, entity.dateJoined,
                entity.dateLastOnline, entity.followersCount, entity.isBookmarked)
    }

    override fun mapToEntity(domain: Player): PlayerEntity {
        return PlayerEntity(domain.username, domain.name, domain.profileUrl,
                domain.title, domain.status, domain.avatar,
                domain.location, domain.country, domain.dateJoined,
                domain.dateLastOnline, domain.followersCount, domain.isBookmarked)
    }

}