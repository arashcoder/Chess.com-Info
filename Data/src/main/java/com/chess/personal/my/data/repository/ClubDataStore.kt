package com.chess.personal.my.data.repository

import com.chess.personal.my.data.model.ClubEntity
import io.reactivex.Single

interface ClubDataStore{
    fun getClubs(countryISO: String): Single<List<String>>
    fun getClub(clubName: String): Single<ClubEntity>
}