package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.BookmarkPlayer
import com.chess.personal.my.domain.usecase.GetBookmarkedPlayers
import com.chess.personal.my.domain.usecase.UnbookmarkPlayer
import com.chess.personal.my.presentation.mapper.PlayerViewMapper
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.Single
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class PlayerHomeViewModel @Inject constructor(
        private val getBookmarkedPlayers: GetBookmarkedPlayers,
        private val bookmarkPlayer: BookmarkPlayer,
        private val unBookmarkPlayer: UnbookmarkPlayer,
        private val mapper: PlayerViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<String>>> =
            MutableLiveData()

    override fun onCleared() {
        getBookmarkedPlayers.dispose()
        bookmarkPlayer.dispose()
        unBookmarkPlayer.dispose()
        super.onCleared()
    }

    fun getPlayers(): LiveData<Resource<List<String>>> {
        return liveData
    }

    fun fetchBookmarkedPlayers() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedPlayers.execute(BookmarkedPlayersSubscriber())
    }

    fun fetchBookmarkedPlayersSingle(): Single<List<String>> {
        return getBookmarkedPlayers.buildUseCaseSingle()
    }

    fun bookmarkPlayer(username: String) {
        //bookmarkPlayer?.execute(FetchBookmarkedPlayersSubscriber(), BookmarkPlayer.Params.forPlayer(username))
        bookmarkPlayer.buildUseCaseCompletable(BookmarkPlayer.Params.forPlayer(username)).blockingGet()
    }

    fun unbookmarkPlayer(username: String) {
        //unBookmarkPlayer?.execute(FetchBookmarkedPlayersSubscriber(), UnbookmarkPlayer.Params.forPlayer(username))
        unBookmarkPlayer.buildUseCaseCompletable(UnbookmarkPlayer.Params.forPlayer(username)).blockingGet()
    }

    inner class BookmarkedPlayersSubscriber: DisposableSingleObserver<List<String>>() {
        override fun onSuccess(t: List<String>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }
    }

    inner class FetchBookmarkedPlayersSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, null, null
                    //t.map { mapper.mapToView(it) }, null)
            ))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

}