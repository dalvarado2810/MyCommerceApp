package com.example.mycommerceapp.di

import com.example.mycommerceapp.data.repositories.SearchRepository
import com.example.mycommerceapp.data.repositories.source.RemoteApiSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRemoteApiSource(retrofit: Retrofit): RemoteApiSource =
        retrofit.create((RemoteApiSource::class.java))

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    fun provideSearchRepository(
        remoteApiSource: RemoteApiSource
    ): SearchRepository =
        SearchRepository(remoteApiSource)
}