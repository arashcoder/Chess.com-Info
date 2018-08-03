package com.chess.personal.my.ui.mapper

import com.chess.personal.my.presentation.model.GameView
import com.chess.personal.my.ui.model.Game
import com.chess.personal.my.ui.model.GamePlayer
import javax.inject.Inject

class GameViewMapper @Inject constructor(): ViewMapper<GameView, Game> {

    override fun mapToView(presentation: GameView): Game {
        return Game(presentation.url, presentation.fen, presentation.png,
                presentation.startTime, presentation.endTime, presentation.timeControl,
                GamePlayer(presentation.whitePlayer.username,
                        presentation.whitePlayer.rating,
                        presentation.whitePlayer.result),
                GamePlayer(presentation.blackPlayer.username,
                        presentation.blackPlayer.rating,
                        presentation.blackPlayer.result)
                )
    }

}