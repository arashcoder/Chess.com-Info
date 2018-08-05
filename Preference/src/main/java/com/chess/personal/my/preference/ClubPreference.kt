package com.chess.personal.my.preference

import com.chess.personal.my.data.repository.ClubPreference
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ClubPreferenceImpl @Inject constructor()
//private val mapper: CachedPla)
    : ClubPreference {
    override fun getBookmarkedClubs(): Single<List<String>> {
        return Single.just(PrefsHelper.getFavoriteClubs().toList())
    }

    override fun setClubAsBookmarked(clubName: String): Completable {
        return Completable.defer {
            PrefsHelper.addFavoriteClub(clubName)
            Completable.complete()
        }
    }

    override fun setClubAsNotBookmarked(clubName: String): Completable {
        return Completable.defer {
            PrefsHelper.removeFavoriteClub(clubName)
            Completable.complete()
        }
    }

}