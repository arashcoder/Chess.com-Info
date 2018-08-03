package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.domain.model.Club
import javax.inject.Inject

open class ClubMapper @Inject constructor() : EntityMapper<ClubEntity, Club> {

    override fun mapFromEntity(entity: ClubEntity): Club {
        return Club(entity.name, entity.avatar, entity.dateCreated, entity.lastActivity,
                entity.visibility, entity.description, entity.countryUrl)
    }

    override fun mapToEntity(domain: Club): ClubEntity {
        return ClubEntity(domain.name, domain.avatar, domain.dateCreated, domain.lastActivity,
                domain.visibility, domain.description, domain.countryUrl)
    }

}