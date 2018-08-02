package com.chess.personal.my.ui.model

import java.util.*

class Player(val username: String="", val name: String?="", val profileUrl: String="", val title: String?="",
             val status: String="", val avatar: String?="", val location: String?="", val country: String="",
             val dateJoined: Date=Date(), val dateLastOnline: Date=Date(), val followersCount: Int=0, val isBookmarked: Boolean=false)