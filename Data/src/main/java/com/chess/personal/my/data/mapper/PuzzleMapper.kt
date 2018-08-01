package com.chess.personal.my.data.mapper

import com.chess.personal.my.data.model.PuzzleEntity
import com.chess.personal.my.domain.model.Puzzle
import javax.inject.Inject

open class PuzzleMapper @Inject constructor() : EntityMapper<PuzzleEntity, Puzzle> {

    override fun mapFromEntity(entity: PuzzleEntity): Puzzle {
        return Puzzle(entity.title, entity.url, entity.imageUrl, entity.datePublished)
    }

    override fun mapToEntity(domain: Puzzle): PuzzleEntity {
        return PuzzleEntity(domain.title, domain.url, domain.imageUrl, domain.datePublished)
    }

}