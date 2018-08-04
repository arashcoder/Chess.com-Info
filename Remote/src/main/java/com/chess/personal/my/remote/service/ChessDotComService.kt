package com.chess.personal.my.remote.service

import com.chess.personal.my.remote.mapper.ClubMemberResponseModelMapper
import com.chess.personal.my.remote.model.*
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChessDotComService{

    companion object {
        const val API_ROOT_URL = "https://api.chess.com/pub/"
    }

    @GET("country/{iso}/players")
    fun getAllPlayersByCountryCode(@Path("iso") countryCode: String): Single<SearchResultsResponseModel>

    @GET("player/{username}")
    fun getPlayer(@Path("username") username: String): Single<PlayerModel>

    @GET("player/{username}/games/archives")
    fun getAllGamesByMonth(@Path("username") username: String): Single<AllGamesResponseModel>

    @GET("player/{username}/games/{year}/{month}")
    fun getMonthlyGames(@Path("username") username: String,
                        @Path("year") year: String,
                        @Path("month") month: String
    )
            : Single<MonthlyGamesResponseModel>

    @GET("country/{iso}/clubs")
    fun getAllClubsByCountryCode(@Path("iso") countryCode: String): Single<SearchResultsResponseModel>

    @GET("club/{club_name}")
    fun getClub(@Path("club_name") clubName: String): Single<ClubModel>

    @GET("club/{club_name}/members")
    fun getAllClubMembers(@Path("club_name") clubName: String): Single<ClubMembersResponseModel>

    @GET("puzzle")
    fun getDailyPuzzle(): Single<PuzzleModel>

    @GET("puzzle/random")
    fun getRandomPuzzle(): Single<PuzzleModel>
}