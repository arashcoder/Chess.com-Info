package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.model.PuzzleEntity
import io.reactivex.Single

interface PlayersRemote {
    fun getPlayers(countryISO: String): Single<List<String>>
}