package com.example.mycommerceapp.data.repositories

import com.example.mycommerceapp.R
import com.example.mycommerceapp.data.model.SearchModel
import com.example.mycommerceapp.data.repositories.source.RemoteApiSource
import com.example.mycommerceapp.viewmodel.AppResource
import retrofit2.Call
import java.lang.Exception
import javax.inject.Inject

class SearchRepository @Inject() constructor(
        private val remoteApiSource: RemoteApiSource) {

    fun getAllRepositories(productName : String?):
            AppResource<SearchModel?> {
            val call: Call<SearchModel> =
                remoteApiSource.getApiSearch(productName)
            return try{
                val response = call.execute()
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    AppResource.Success(searchResponse)
                }else{
                    AppResource.Error("Problemas de conexión")
                }
            } catch (e: Exception) {
                AppResource.Error("No estas conectado a internet..")
            }
    }
}