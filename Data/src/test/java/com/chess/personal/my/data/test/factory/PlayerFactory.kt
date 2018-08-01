package com.chess.personal.my.data.test.factory

import com.chess.personal.my.data.model.PlayerEntity
import com.chess.personal.my.domain.model.Player

object PlayerFactory {

    fun makePlayerEntity(): String {
        return DataFactory.randomString()
//        return PlayerEntity(DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(),DataFactory.randomDate(),
//                DataFactory.randomDate(), DataFactory.randomInt(),
//                DataFactory.randomBoolean())
    }

    fun makePlayer(): String {
        return DataFactory.randomString()
//        return Player(DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(), DataFactory.randomString(),
//                DataFactory.randomString(),DataFactory.randomDate(),
//                DataFactory.randomDate(), DataFactory.randomInt(),
//                DataFactory.randomBoolean())
    }

}