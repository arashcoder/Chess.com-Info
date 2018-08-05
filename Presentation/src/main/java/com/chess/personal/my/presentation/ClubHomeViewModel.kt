package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.interactor.club.BookmarkClub
import com.chess.personal.my.domain.interactor.club.GetBookmarkedClubs
import com.chess.personal.my.domain.interactor.club.UnbookmarkClub
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.Single
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class ClubHomeViewModel @Inject constructor(
        private val getBookmarkedClubs: GetBookmarkedClubs,
        private val bookmarkClub: BookmarkClub,
        private val unBookmarkClub: UnbookmarkClub): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<String>>> =
            MutableLiveData()

    override fun onCleared() {
        getBookmarkedClubs.dispose()
        bookmarkClub.dispose()
        unBookmarkClub.dispose()
        super.onCleared()
    }

    fun getClubs(): LiveData<Resource<List<String>>> {
        return liveData
    }

    fun fetchBookmarkedClubs() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedClubs.execute(BookmarkedClubsSubscriber())
    }

    fun fetchBookmarkedClubsSingle(): Single<List<String>> {
        return getBookmarkedClubs.buildUseCaseSingle()
    }

    fun bookmarkClub(username: String) {
        return bookmarkClub.execute(BookmarkClubsSubscriber(),
                BookmarkClub.Params.forClub(username))
    }

    fun unbookmarkClub(username: String) {
        return unBookmarkClub.execute(BookmarkClubsSubscriber(),
                UnbookmarkClub.Params.forClub(username))
    }

    inner class BookmarkedClubsSubscriber : DisposableSingleObserver<List<String>>() {
        override fun onSuccess(t: List<String>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }
    }

    inner class BookmarkClubsSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }

    }
}