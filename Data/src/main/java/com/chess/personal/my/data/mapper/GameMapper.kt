package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.GamePlayerEntity
import com.chess.personal.my.domain.model.Game
import com.chess.personal.my.domain.model.GamePlayer
import java.util.*
import javax.inject.Inject

open class GameMapper @Inject constructor() : EntityMapper<GameEntity, Game> {

    override fun mapFromEntity(entity: GameEntity): Game {
        return Game(entity.url, entity.fen, entity.png, entity.startTime,
                entity.endTime, entity.timeControl,
                GamePlayer(entity.whitePlayer.username,
                        entity.whitePlayer.rating,
                        entity.whitePlayer.result),
                GamePlayer(entity.blackPlayer.username,
                        entity.blackPlayer.rating,
                        entity.blackPlayer.result)
        )
    }

    override fun mapToEntity(domain: Game): GameEntity {
        return GameEntity(domain.url, domain.fen, domain.png, domain.startTime,
                domain.endTime, domain.timeControl,
                GamePlayerEntity(domain.whitePlayer.username,
                        domain.whitePlayer.rating,
                        domain.whitePlayer.result),
                GamePlayerEntity(domain.blackPlayer.username,
                        domain.blackPlayer.rating,
                        domain.blackPlayer.result)
        )
    }

}