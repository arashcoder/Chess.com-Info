package com.chess.personal.my.data

import com.chess.personal.my.data.mapper.ClubMapper
import com.chess.personal.my.data.store.ClubDataStoreFactory
import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Single
import javax.inject.Inject

class ClubDataRepository @Inject constructor(
        private val factory: ClubDataStoreFactory,
        private val mapper: ClubMapper)
    : ClubRepository {
    override fun getClub(clubName: String): Single<Club> {
        return factory.getDataStore().getClub(clubName)
                .map { mapper.mapFromEntity(it) }
    }

    override fun getClubs(countryISO: String): Single<List<String>> {
        return factory.getDataStore().getClubs(countryISO)
    }


}