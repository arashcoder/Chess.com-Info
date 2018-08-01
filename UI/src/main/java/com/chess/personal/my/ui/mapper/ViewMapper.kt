package com.chess.personal.my.ui.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}