package com.chess.personal.my.domain.model

import java.util.*

class Club(val name: String, val avatar: String?="", val dateCreated: Date, val lastActivity: Date,
           val visibility: String, val description: String, val countryUrl: String)