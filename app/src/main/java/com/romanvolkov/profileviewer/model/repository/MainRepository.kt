package com.romanvolkov.profileviewer.model.repository

import com.romanvolkov.profileviewer.Resource
import com.romanvolkov.profileviewer.model.api.ProfileApi
import com.romanvolkov.profileviewer.model.entity.ProfileEntity
import io.reactivex.Observable


interface IMainRepository {
    fun getProfiles(count: Int): Observable<Resource<ProfileEntity>>
}

class MainRepository(private val api: ProfileApi) : IMainRepository {
    override fun getProfiles(count: Int): Observable<Resource<ProfileEntity>> {
        return api
            .getProfiles(count = count)
            .map { Resource.Data(it) as Resource<ProfileEntity> }
            .onErrorReturn { Resource.Error(it) }
    }
}