package com.chess.personal.my.ui.test.factory

import com.chess.personal.my.domain.model.Player

object PlayerDataFactory {

    fun makePlayer(): Player {
        return Player(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomDate(), TestDataFactory.randomDate(), TestDataFactory.randomInt(),
                TestDataFactory.randomBoolean())
    }

}