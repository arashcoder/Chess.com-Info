package com.chess.personal.my.remote


import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.data.repository.PlayersRemote
import com.chess.personal.my.remote.mapper.PlayersResponseModelMapper
import com.chess.personal.my.remote.model.SearchResultsResponseModel
import com.chess.personal.my.remote.service.ChessDotComService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PlayersRemoteImpl @Inject constructor(
        private val service: ChessDotComService,
        private val mapper: PlayersResponseModelMapper)
    : PlayersRemote {

    override fun getPlayers(countryISO: String): Single<List<String>> {
        return service.getAllPlayersByCountryCode(countryISO)
                .map {
                    //it.items.map { mapper.mapFromModel(it)
                    it.players
                }
    }

}
