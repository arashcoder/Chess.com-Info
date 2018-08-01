package com.chess.personal.my.domain.model

import java.util.*

class Player (val username: String, val name: String?="", val profileUrl: String, val title: String?="",
              val status: String, val avatar: String?="", val location: String?="", val country: String,
              val dateJoined: Date, val dateLastOnline: Date, val followersCount: Int, val isBookmarked: Boolean)
