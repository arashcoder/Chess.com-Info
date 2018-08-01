package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.interactor.bookmark.BookmarkPlayer
import com.chess.personal.my.domain.interactor.bookmark.UnbookmarkPlayer
import com.chess.personal.my.domain.interactor.browse.GetPlayers
import com.chess.personal.my.domain.model.Player
import com.chess.personal.my.presentation.mapper.PlayerViewMapper
import com.chess.personal.my.presentation.model.PlayerView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class BrowsePlayersViewModel @Inject internal constructor(
        private val getPlayers: GetPlayers?,
        private val bookmarkPlayer: BookmarkPlayer,
        private val unBookmarkPlayer: UnbookmarkPlayer,
        private val mapper: PlayerViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    init {
        //fetchPlayers("IR") //TODO
    }

    override fun onCleared() {
        getPlayers?.dispose()
        super.onCleared()
    }

    fun getPlayers(): LiveData<Resource<List<String>>> {
        return liveData
    }

    fun fetchPlayers(countryISO: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getPlayers?.execute(PlayersSubscriber(),
                GetPlayers.Params.forPlayer(countryISO))
    }

    fun fetchBookmarkedPlayers() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getPlayers?.execute(PlayersSubscriber())
    }

    fun bookmarkPlayer(username: String) {
        return bookmarkPlayer.execute(BookmarkPlayersSubscriber(),
                BookmarkPlayer.Params.forPlayer(username))
    }

    fun unbookmarkPlayer(username: String) {
        return unBookmarkPlayer.execute(BookmarkPlayersSubscriber(),
                UnbookmarkPlayer.Params.forPlayer(username))
    }

    inner class PlayersSubscriber(): DisposableSingleObserver<List<String>>() {
        override fun onSuccess(t: List<String>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t, null
                    //t.map { mapper.mapToView(it) }, null)
            ))
        }

//        override fun onNext(t: List<String>) {
//            liveData.postValue(Resource(ResourceState.SUCCESS, t, null
//                    //t.map { mapper.mapToView(it) }, null)
//            ))
//        }

        //override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkPlayersSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }

    }
}