package com.romanvolkov.profileviewer.model.api

import com.romanvolkov.profileviewer.model.entity.ProfileEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProfileApi {
    @GET("api")
    @Headers("Accept: application/json")
    fun getProfiles(
        @Query("results") count: Int = 20
    ): Observable<ProfileEntity>
}