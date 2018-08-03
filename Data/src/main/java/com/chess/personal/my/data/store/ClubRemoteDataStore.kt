package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.data.repository.ClubDataStore
import com.chess.personal.my.data.repository.ClubRemote
import io.reactivex.Single
import javax.inject.Inject

open class ClubRemoteDataStore @Inject constructor(
        private val clubRemote: ClubRemote)
    : ClubDataStore {
    override fun getClub(clubName: String): Single<ClubEntity> {
        return clubRemote.getClub(clubName)
    }

    override fun getClubs(countryISO: String): Single<List<String>> {
        return clubRemote.getClubs(countryISO)
    }

}