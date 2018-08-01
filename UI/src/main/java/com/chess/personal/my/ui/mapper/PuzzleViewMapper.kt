package com.chess.personal.my.ui.mapper

import com.chess.personal.my.presentation.model.PuzzleView
import com.chess.personal.my.ui.model.Puzzle
import javax.inject.Inject

class PuzzleViewMapper @Inject constructor(): ViewMapper<PuzzleView, Puzzle> {

    override fun mapToView(presentation: PuzzleView): Puzzle {
        return Puzzle(presentation.title, presentation.url, presentation.imageUrl, presentation.datePublished, presentation.isDaily)
    }

}