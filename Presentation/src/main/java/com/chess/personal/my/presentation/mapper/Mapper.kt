package com.chess.personal.my.presentation.mapper

interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}