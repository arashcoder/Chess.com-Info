package com.chess.personal.my.domain.repository

import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.domain.model.ClubMember
import io.reactivex.Completable
import io.reactivex.Single

interface ClubRepository{
    fun getClubs(countryISO: String): Single<List<String>>
    fun getClub(clubName: String): Single<Club>
    fun getClubMembers(clubName: String): Single<List<ClubMember>>
    fun getBookmarkedClubs(): Single<List<String>>
    fun bookmarkClub(clubName: String): Completable
    fun unbookmarkClub(clubName: String): Completable
}