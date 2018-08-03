package com.chess.personal.my.domain.model

import java.util.*

class Game(val url: String, val fen: String, val png: String,
           val startTime: Date=Date(), val endTime: Date, val timeControl: String,
           val whitePlayer: GamePlayer, val blackPlayer: GamePlayer)

class GamePlayer(val username: String, val rating: Int, val result: String)