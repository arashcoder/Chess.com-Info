package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetClubMembers
import com.chess.personal.my.domain.model.ClubMember
import com.chess.personal.my.presentation.mapper.ClubMemberViewMapper
import com.chess.personal.my.presentation.model.ClubMemberView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class ClubMembersViewModel @Inject internal constructor(
        private val getClubMembers: GetClubMembers?,
        private val mapper: ClubMemberViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ClubMemberView>>> = MutableLiveData()

    override fun onCleared() {
        getClubMembers?.dispose()
        super.onCleared()
    }

    fun getClubMembers(): LiveData<Resource<List<ClubMemberView>>> {
        return liveData
    }

    fun fetchClubMembers(clubName: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getClubMembers?.execute(ClubMemberSubscriber(),
                GetClubMembers.Params.forGameMember(clubName))
    }

    inner class ClubMemberSubscriber : DisposableSingleObserver<List<ClubMember>>() {
        override fun onSuccess(t: List<ClubMember>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }, null
            ))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}
