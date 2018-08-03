package com.chess.personal.my.remote

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.data.repository.ClubRemote
import com.chess.personal.my.remote.mapper.ClubResponseModelMapper
import com.chess.personal.my.remote.service.ChessDotComService
import io.reactivex.Single
import javax.inject.Inject

class ClubRemoteImpl @Inject constructor(
        private val service: ChessDotComService,
        private val mapper: ClubResponseModelMapper)
    : ClubRemote {
    override fun getClub(clubName: String): Single<ClubEntity> {
        return service.getClub(clubName).map { mapper.mapFromModel(it) }
    }

    override fun getClubs(countryISO: String): Single<List<String>> {
        return service.getAllClubsByCountryCode(countryISO).map { it.clubs }
    }
}