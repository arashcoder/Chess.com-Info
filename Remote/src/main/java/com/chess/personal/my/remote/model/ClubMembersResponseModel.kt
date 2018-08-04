package com.chess.personal.my.remote.model

import com.squareup.moshi.Json

open class ClubMembersResponseModel {
    @field:Json(name = "all_time")
    var members: List<ClubMemberModel> = listOf()
}