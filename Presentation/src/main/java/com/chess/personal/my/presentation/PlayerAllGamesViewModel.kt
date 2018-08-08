package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetAllGames
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class PlayerAllGamesViewModel @Inject internal constructor(
        private val getPlayers: GetAllGames?): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    override fun onCleared() {
        getPlayers?.dispose()
        super.onCleared()
    }

    fun getGames(): LiveData<Resource<List<String>>> {
        return liveData
    }

    fun fetchAllGames(username: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getPlayers?.execute(PlayersSubscriber(),
                GetAllGames.Params.forPlayer(username))
    }

    inner class PlayersSubscriber: DisposableSingleObserver<List<String>>() {
        override fun onSuccess(t: List<String>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t, null
            ))
        }


        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

}