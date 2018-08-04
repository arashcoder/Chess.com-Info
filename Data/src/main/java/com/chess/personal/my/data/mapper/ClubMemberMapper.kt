package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.ClubMemberEntity
import com.chess.personal.my.domain.model.ClubMember
import javax.inject.Inject

open class ClubMemberMapper @Inject constructor() : EntityMapper<ClubMemberEntity, ClubMember> {

    override fun mapFromEntity(entity: ClubMemberEntity): ClubMember {
        return ClubMember(entity.username, entity.dateJoined)
    }

    override fun mapToEntity(domain: ClubMember): ClubMemberEntity {
        return ClubMemberEntity(domain.username, domain.dateJoined)
    }

}