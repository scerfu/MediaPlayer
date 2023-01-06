package com.example.musicapi.model.remote

import com.example.musicapi.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET(ENDPOINT)
    // https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
    fun getMusicSong(
        @Query(PARAM_TERM)  musicTerm: String
    ): Call<MusicResponse>
}