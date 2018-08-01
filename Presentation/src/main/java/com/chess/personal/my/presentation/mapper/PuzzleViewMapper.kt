package com.chess.personal.my.presentation.mapper

import com.chess.personal.my.domain.model.Puzzle
import com.chess.personal.my.presentation.model.PuzzleView
import javax.inject.Inject

open class PuzzleViewMapper @Inject constructor() : Mapper<PuzzleView, Puzzle> {

    override fun mapToView(type: Puzzle): PuzzleView {
        return PuzzleView(type.title, type.url, type.imageUrl, type.datePublished)
    }
}