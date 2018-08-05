package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.data.model.ClubMemberEntity
import com.chess.personal.my.data.repository.ClubDataStore
import com.chess.personal.my.data.repository.ClubPreference
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class ClubPreferenceDataStore @Inject constructor(
        private val clubPreference: ClubPreference)
    : ClubDataStore {
    override fun getClubs(countryISO: String): Single<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClub(clubName: String): Single<ClubEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClubMembers(clubName: String): Single<List<ClubMemberEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedClubs(): Single<List<String>> {
        return clubPreference.getBookmarkedClubs()
    }

    override fun setClubAsBookmarked(clubName: String): Completable {
        return clubPreference.setClubAsBookmarked(clubName)
    }

    override fun setClubAsNotBookmarked(clubName: String): Completable {
        return clubPreference.setClubAsNotBookmarked(clubName)
    }


}