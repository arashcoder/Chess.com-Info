package com.chess.personal.my.data.model

import java.util.*

class ClubEntity(val name: String, val avatar: String?="", val dateCreated: Date, val lastActivity: Date,
                 val visibility: String, val description: String, val countryUrl: String)