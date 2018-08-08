package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetPlayer
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.presentation.mapper.PlayerViewMapper
import com.chess.personal.my.presentation.model.PlayerView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class PlayerProfileViewModel @Inject internal constructor(
        private val getPlayer: GetPlayer?,
        private val mapper: PlayerViewMapper): ViewModel()
{

    private val liveData: MutableLiveData<Resource<PlayerView>> = MutableLiveData()

    override fun onCleared() {
        getPlayer?.dispose()
        super.onCleared()
    }

    fun getPlayer(): LiveData<Resource<PlayerView>> {
        return liveData
    }

    fun fetchPlayerProfile(username: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getPlayer?.execute(PlayerSubscriber(), GetPlayer.Params.forPlayer(username))
    }

    inner class PlayerSubscriber(): DisposableSingleObserver<Player>() {
        override fun onSuccess(t: Player) {
            val player = mapper.mapToView(t)
            liveData.postValue(Resource(ResourceState.SUCCESS,  player , null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

    }
}