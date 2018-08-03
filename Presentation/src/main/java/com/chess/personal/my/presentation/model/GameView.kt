package com.chess.personal.my.presentation.model

import java.util.*

class GameView(val url: String, val fen: String, val png: String,
               val startTime: Date = Date(), val endTime: Date, val timeControl: String,
               val whitePlayer: GamePlayerView, val blackPlayer: GamePlayerView)

class GamePlayerView(val username: String, val rating: Int, val result: String)