package com.example.mycommerceapp.data.repositories.source

import com.example.mycommerceapp.data.model.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiSource {

    @GET("MLC/search")
    fun getApiSearch(
        @Query("q") productName : String?
    ): Call<SearchModel>
}