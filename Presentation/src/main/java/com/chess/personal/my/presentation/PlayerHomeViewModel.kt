package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.interactor.bookmark.BookmarkPlayer
import com.chess.personal.my.domain.interactor.bookmark.GetBookmarkedPlayers
import com.chess.personal.my.domain.interactor.bookmark.UnbookmarkPlayer
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
        return bookmarkPlayer.execute(BookmarkPlayersSubscriber(),
                BookmarkPlayer.Params.forPlayer(username))
    }

    fun unbookmarkPlayer(username: String) {
        return unBookmarkPlayer.execute(BookmarkPlayersSubscriber(),
                UnbookmarkPlayer.Params.forPlayer(username))
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