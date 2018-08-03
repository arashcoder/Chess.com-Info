package com.chess.personal.my.ui.model

import java.util.*

class Game(val url: String, val fen: String, val png: String,
           val startTime: Date = Date(), val endTime: Date, val timeControl: String,
           val whitePlayer: GamePlayer, val blackPlayer: GamePlayer){

    fun getOpponent(username: String): String{
        return if(whitePlayer.username == username) blackPlayer.username else whitePlayer.username
    }

    fun getResult(username: String): String{
        if(whitePlayer.username == username){
            return if(whitePlayer.result == "win") "Won" else "Lost"
        }
        return if(blackPlayer.result == "win") "Won" else "Lost"
    }
}

class GamePlayer(val username: String, val rating: Int, val result: String)