package com.chess.personal.my.remote


import com.chess.personal.my.data.model.GameEntity
import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.data.repository.PlayersRemote
import com.chess.personal.my.remote.mapper.GameResponseModelMapper
import com.chess.personal.my.remote.mapper.PlayersResponseModelMapper
import com.chess.personal.my.remote.model.SearchResultsResponseModel
import com.chess.personal.my.remote.service.ChessDotComService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PlayersRemoteImpl @Inject constructor(
        private val service: ChessDotComService,
        private val mapper: PlayersResponseModelMapper,
        private val gameMapper: GameResponseModelMapper)
    : PlayersRemote {
    override fun getMonthlyGames(username: String, year: String, month: String): Single<List<GameEntity>> {
        return service.getMonthlyGames(username, year, month)
                .map { it.games.map { gameMapper.mapFromModel(it)  }
                }
    }

    override fun getAllGames(username: String): Single<List<String>> {
        return service.getAllGamesByMonth(username)
                .map {
                    //it.items.map { mapper.mapFromModel(it)
                    it.archives
                }
    }

    override fun getPlayer(username: String): Single<PlayerEntity> {
        return service.getPlayer(username).map { mapper.mapFromModel(it) }
    }

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return service.getAllPlayersByCountryCode(countryISO)
                .map {
                    //it.items.map { mapper.mapFromModel(it)
                    it.players
                }
    }

}
