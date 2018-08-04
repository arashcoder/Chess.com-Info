package com.chess.personal.my.remote.mapper

import com.chess.personal.my.data.model.ClubMemberEntity
import com.chess.personal.my.remote.model.ClubMemberModel
import javax.inject.Inject

class ClubMemberResponseModelMapper @Inject constructor(): ModelMapper<ClubMemberModel, ClubMemberEntity> {

    override fun mapFromModel(model: ClubMemberModel): ClubMemberEntity {
        return ClubMemberEntity(model.username, model.dateJoined)
    }

}