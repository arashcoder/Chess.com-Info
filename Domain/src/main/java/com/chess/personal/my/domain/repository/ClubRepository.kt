package com.chess.personal.my.domain.repository

import com.chess.personal.my.domain.model.Club
import io.reactivex.Single

interface ClubRepository{
    fun getClubs(countryISO: String): Single<List<String>>
    fun getClub(clubName: String): Single<Club>
}