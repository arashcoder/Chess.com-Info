package com.chess.personal.my.domain.test

import com.chess.personal.my.domain.model.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object PlayerDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomDate(): Date {
        return Date()
    }

    fun randomInt() : Int{
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makePlayer(): String {
        return randomUuid()
//        return Player(randomUuid(), randomUuid(), randomUuid(),
//                randomUuid(), randomUuid(), randomUuid(),
//                randomUuid(), randomUuid(), randomDate(),
//                randomDate(), randomInt(), randomBoolean())
    }

    fun makePlayerList(count: Int): List<String> {
        val players = mutableListOf<String>()
        repeat(count) {
            players.add(makePlayer())
        }
        return players
    }

}