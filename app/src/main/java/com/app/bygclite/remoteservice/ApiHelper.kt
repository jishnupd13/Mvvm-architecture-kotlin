package com.app.bygclite.remoteservice

import com.app.bygclite.models.TestApiResponseModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(): Response<List<TestApiResponseModel>>


}