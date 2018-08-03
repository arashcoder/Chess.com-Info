package com.chess.personal.my.remote.mapper

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.remote.model.ClubModel
import javax.inject.Inject

class ClubResponseModelMapper @Inject constructor(): ModelMapper<ClubModel, ClubEntity> {

    override fun mapFromModel(model: ClubModel): ClubEntity {
        return ClubEntity(model.name, model.avatar, model.created,
                model.lastActivity, model.visibility, model.description, model.countryUrl)
    }

}