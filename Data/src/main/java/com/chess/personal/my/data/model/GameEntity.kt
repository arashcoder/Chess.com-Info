package com.chess.personal.my.data.model

import java.util.*

class GameEntity(val url: String, val fen: String, val png: String,
                 val startTime: Date=Date(), val endTime: Date, val timeControl: String,
                 val whitePlayer: GamePlayerEntity, val blackPlayer: GamePlayerEntity)

class GamePlayerEntity(val username: String, val rating: Int, val result: String)