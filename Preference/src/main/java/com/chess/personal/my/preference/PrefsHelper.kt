package com.chess.personal.my.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PrefsHelper {

    private const val KEY_FAV_PLAYERS = "favorite_players"
    private const val KEY_FAV_CLUBS = "favorite_clubs"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        if (context !is Application) {
            throw IllegalArgumentException("This should be the application context. Not the activity context")
        }
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun addFavoritePlayer(username: String) {
        val favoritePlayers = getFavoritePlayers()
        val players = favoritePlayers.plus(username)
        prefs.edit()
                .putStringSet(KEY_FAV_PLAYERS, players)
                .apply()
    }

    fun getFavoritePlayers(): Set<String> {
        return prefs.getStringSet(KEY_FAV_PLAYERS, setOf())
    }

    fun removeFavoritePlayer(username: String) {
        val favoritePlayers = getFavoritePlayers()
        val players = favoritePlayers.minus(username)
        prefs.edit()
                .putStringSet(KEY_FAV_PLAYERS, players)
                .apply()
    }

    fun addFavoriteClub(clubName: String) {
        val favoriteClubs = getFavoriteClubs()
        val clubs = favoriteClubs.plus(clubName)
        prefs.edit()
                .putStringSet(KEY_FAV_CLUBS, clubs)
                .apply()
    }

    fun getFavoriteClubs(): Set<String> {
        return prefs.getStringSet(KEY_FAV_CLUBS, setOf())
    }

    fun removeFavoriteClub(clubName: String) {
        val favoriteClubs = getFavoriteClubs()
        val clubs = favoriteClubs.minus(clubName)
        prefs.edit()
                .putStringSet(KEY_FAV_CLUBS, clubs)
                .apply()
    }
}