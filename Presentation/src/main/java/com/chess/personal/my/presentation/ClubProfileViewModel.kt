package com.chess.personal.my.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chess.personal.my.domain.usecase.GetClub
import com.chess.personal.my.domain.model.Club
import com.chess.personal.my.presentation.mapper.ClubViewMapper
import com.chess.personal.my.presentation.model.ClubView
import com.chess.personal.my.presentation.state.Resource
import com.chess.personal.my.presentation.state.ResourceState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

open class ClubProfileViewModel @Inject internal constructor(
        private val getClub: GetClub?,
        private val mapper: ClubViewMapper): ViewModel()
{

    private val liveData: MutableLiveData<Resource<ClubView>> = MutableLiveData()

    override fun onCleared() {
        getClub?.dispose()
        super.onCleared()
    }

    fun getClub(): LiveData<Resource<ClubView>> {
        return liveData
    }

    fun fetchClubProfile(clubName: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getClub?.execute(ClubSubscriber(), GetClub.Params.forClub(clubName))
    }

    inner class ClubSubscriber: DisposableSingleObserver<Club>() {
        override fun onSuccess(t: Club) {
            val club = mapper.mapToView(t)
            liveData.postValue(Resource(ResourceState.SUCCESS,  club , null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

    }
}