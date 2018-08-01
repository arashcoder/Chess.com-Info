package com.chess.personal.my.preference

import com.chess.personal.my.data.repository.PlayersPreference
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PlayersPreferenceImpl @Inject constructor()
//private val mapper: CachedPla)
    : PlayersPreference {
    override fun getBookmarkedPlayers(): Single<List<String>> {
        return Single.just(PrefsHelper.getFavoritePlayers().toList())
    }

    override fun setPlayerAsBookmarked(username: String): Completable {
        return Completable.defer {
            PrefsHelper.addFavoritePlayer(username)
            Completable.complete()
        }
    }

    override fun setPlayerAsNotBookmarked(username: String): Completable {
        return Completable.defer {
            PrefsHelper.removeFavoritePlayer(username)
            Completable.complete()
        }
    }

}