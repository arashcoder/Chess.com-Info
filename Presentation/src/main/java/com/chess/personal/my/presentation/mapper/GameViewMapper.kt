package com.chess.personal.my.presentation.mapper

import com.chess.personal.my.domain.model.Game
import com.chess.personal.my.presentation.model.GamePlayerView
import com.chess.personal.my.presentation.model.GameView
import javax.inject.Inject

open class GameViewMapper @Inject constructor() : Mapper<GameView, Game> {

    override fun mapToView(type: Game): GameView {
        return GameView(type.url, type.fen, type.png, type.startTime,
                type.endTime, type.timeControl,
                GamePlayerView(type.whitePlayer.username,
                        type.whitePlayer.rating,
                        type.whitePlayer.result),
                GamePlayerView(type.blackPlayer.username,
                        type.blackPlayer.rating,
                        type.blackPlayer.result)
                )
    }
}