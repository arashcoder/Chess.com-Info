package com.chess.personal.my.data.store

import com.chess.personal.my.data.repository.ClubDataStore
import com.chess.personal.my.data.repository.ClubRemote
import javax.inject.Inject

open class ClubDataStoreFactory @Inject constructor(
        private val clubRemoteDataStore: ClubRemoteDataStore,
        private val clubPrefDataStore: ClubPreferenceDataStore) {

    open fun getDataStore(): ClubDataStore {
        return clubRemoteDataStore
    }

    open fun getPreferenceDataStore(): ClubDataStore{
        return clubPrefDataStore
    }

}