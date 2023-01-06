package com.example.musicapi.model.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    val api: MusicService by lazy {
        initRetroFit().create(MusicService::class.java)
    }

    private fun initRetroFit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOKHttpClient())
            .build()
    }

    private fun createOKHttpClient(): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
}