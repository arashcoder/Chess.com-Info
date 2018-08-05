package com.chess.personal.my.data.store

import com.chess.personal.my.data.model.ClubEntity
import com.chess.personal.my.data.model.ClubMemberEntity
import com.chess.personal.my.data.repository.ClubDataStore
import com.chess.personal.my.data.repository.ClubRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class ClubRemoteDataStore @Inject constructor(
        private val clubRemote: ClubRemote)
    : ClubDataStore {
    override fun getBookmarkedClubs(): Single<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClubAsBookmarked(clubName: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClubAsNotBookmarked(clubName: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClubMembers(clubName: String): Single<List<ClubMemberEntity>> {
        return clubRemote.getClubMembers(clubName)
    }

    override fun getClub(clubName: String): Single<ClubEntity> {
        return clubRemote.getClub(clubName)
    }

    override fun getClubs(countryISO: String): Single<List<String>> {
        return clubRemote.getClubs(countryISO)
    }

}