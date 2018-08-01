package com.chess.personal.my.remote.service

import com.chess.personal.my.remote.model.PuzzleModel
import com.chess.personal.my.remote.model.SearchResultsResponseModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ChessDotComService{

    companion object {
        const val API_ROOT_URL = "https://api.chess.com/pub/"
    }

    @GET("country/{iso}/players")
    fun getAllPlayersByCountryCode(@Path("iso") countryCode: String): Single<SearchResultsResponseModel>

    @GET("puzzle")
    fun getDailyPuzzle(): Single<PuzzleModel>

    @GET("puzzle/random")
    fun getRandomPuzzle(): Single<PuzzleModel>
}