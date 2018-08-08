package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetDailyPuzzle
import com.chess.personal.my.domain.usecase.GetRandomPuzzle
import com.chess.personal.my.domain.model.Puzzle
import com.chess.personal.my.presentation.mapper.PuzzleViewMapper
import com.chess.personal.my.presentation.model.PuzzleView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class HomeViewModel @Inject internal constructor(
        private val getDailyPuzzle: GetDailyPuzzle?,
        private val getRandomPuzzle: GetRandomPuzzle?,
        private val mapper: PuzzleViewMapper): ViewModel()
{

    private val liveData: MutableLiveData<Resource<PuzzleView>> = MutableLiveData()
    private val liveDataRandom: MutableLiveData<Resource<PuzzleView>> = MutableLiveData()

    override fun onCleared() {
        getDailyPuzzle?.dispose()
        getRandomPuzzle?.dispose()
        super.onCleared()
    }

    fun getPuzzle(): LiveData<Resource<PuzzleView>> {
        return liveData
    }

    fun getRandomPuzzle(): LiveData<Resource<PuzzleView>> {
        return liveDataRandom
    }

    fun fetchDailyPuzzle() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getDailyPuzzle?.execute(PuzzleSubscriber())
    }

    fun fetchRandomPuzzle() {
        liveDataRandom.postValue(Resource(ResourceState.LOADING, null, null))
        getRandomPuzzle?.execute(Puzzle2Subscriber())
    }

    inner class PuzzleSubscriber(): DisposableSingleObserver<Puzzle>() {
        override fun onSuccess(t: Puzzle) {
            val puzzle = mapper.mapToView(t)
            puzzle.isDaily = true
            liveData.postValue(Resource(ResourceState.SUCCESS,  puzzle , null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

    }


    inner class Puzzle2Subscriber(): DisposableSingleObserver<Puzzle>() {
        override fun onSuccess(t: Puzzle) {
            val puzzle = mapper.mapToView(t)
            puzzle.isDaily = false
            liveDataRandom.postValue(Resource(ResourceState.SUCCESS,  puzzle , null))
        }

        override fun onError(e: Throwable) {
            liveDataRandom.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

    }
}