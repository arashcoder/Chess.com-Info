package com.chess.personal.my.data

import com.chess.personal.my.data.mapper.ClubMapper
import com.chess.personal.my.data.mapper.ClubMemberMapper
import com.chess.personal.my.data.store.ClubDataStoreFactory
import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.domain.model.ClubMember
import com.chess.personal.my.domain.repository.ClubRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ClubDataRepository @Inject constructor(
        private val factory: ClubDataStoreFactory,
        private val mapper: ClubMapper,
        private val memberMapper: ClubMemberMapper)
    : ClubRepository {

    override fun getBookmarkedClubs(): Single<List<String>> {
        return factory.getPreferenceDataStore().getBookmarkedClubs()
    }

    override fun bookmarkClub(clubName: String): Completable {
        return factory.getPreferenceDataStore().setClubAsBookmarked(clubName)
    }

    override fun unbookmarkClub(clubName: String): Completable {
        return factory.getPreferenceDataStore().setClubAsNotBookmarked(clubName)
    }

    override fun getClubMembers(clubName: String): Single<List<ClubMember>> {
        return factory.getDataStore().getClubMembers(clubName)
                .map { it.map { memberMapper.mapFromEntity(it) } }
    }

    override fun getClub(clubName: String): Single<Club> {
        return factory.getDataStore().getClub(clubName)
                .map { mapper.mapFromEntity(it) }
    }

    override fun getClubs(countryISO: String): Single<List<String>> {
        return factory.getDataStore().getClubs(countryISO)
    }


}