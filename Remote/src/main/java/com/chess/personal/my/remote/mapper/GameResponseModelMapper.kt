package com.chess.personal.my.remote.mapper

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.GamePlayerEntity
import com.chess.personal.my.remote.model.GameModel
import javax.inject.Inject

class GameResponseModelMapper @Inject constructor(): ModelMapper<GameModel, GameEntity> {

    override fun mapFromModel(model: GameModel): GameEntity {
        return GameEntity(model.url, model.fen, model.fen, model.startTime,
                model.endTime, model.timeControl,
                GamePlayerEntity(model.white.username,
                        model.white.rating,
                        model.white.result),
                GamePlayerEntity(model.black.username,
                        model.black.rating,
                        model.black.result)
        )
    }

}