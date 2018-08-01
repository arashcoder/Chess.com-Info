package com.chess.personal.my.presentation.model

import java.util.*

class PlayerView(val username: String, val name: String?="", val profileUrl: String, val title: String?="",
val status: String, val avatar: String?="", val location: String?="", val country: String,
val dateJoined: Date, val dateLastOnline: Date, val followersCount: Int, val isBookmarked: Boolean)