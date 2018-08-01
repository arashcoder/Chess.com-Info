package com.chess.personal.my.remote.mapper

import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.remote.model.PuzzleModel
import javax.inject.Inject

class PuzzleResponseModelMapper @Inject constructor(): ModelMapper<PuzzleModel, PuzzleEntity> {

    override fun mapFromModel(model: PuzzleModel): PuzzleEntity {
        return PuzzleEntity(model.title, model.url, model.imageUrl, model.datePublished)
    }

}