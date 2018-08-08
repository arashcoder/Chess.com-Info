package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetMonthlyGames
import com.chess.personal.my.domain.model.Game
import com.chess.personal.my.presentation.mapper.GameViewMapper
import com.chess.personal.my.presentation.model.GameView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class PlayerMonthlyGamesViewModel @Inject internal constructor(
        private val getMonthlyGames: GetMonthlyGames?,
        private val mapper: GameViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<GameView>>> = MutableLiveData()

    override fun onCleared() {
        getMonthlyGames?.dispose()
        super.onCleared()
    }

    fun getGames(): LiveData<Resource<List<GameView>>> {
        return liveData
    }

    fun fetchMonthlyGames(username: String, year: String, month: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getMonthlyGames?.execute(PlayersSubscriber(),
                GetMonthlyGames.Params.forGame(username, year, month))
    }

    inner class PlayersSubscriber: DisposableSingleObserver<List<Game>>() {
        override fun onSuccess(t: List<Game>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }, null
            ))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

}