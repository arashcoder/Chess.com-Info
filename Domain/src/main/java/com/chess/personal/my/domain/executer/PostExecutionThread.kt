package com.chess.personal.my.domain.executer

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}