package com.chess.personal.my.data.repository

import io.reactivex.Completable
import io.reactivex.Single

interface ClubPreference{
    fun getBookmarkedClubs(): Single<List<String>>
    fun setClubAsBookmarked(clubName: String): Completable
    fun setClubAsNotBookmarked(clubName: String): Completable
}