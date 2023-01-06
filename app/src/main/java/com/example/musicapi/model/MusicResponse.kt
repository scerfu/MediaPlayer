package com.example.musicapi.model

data class MusicResponse(
   val results: List<Song>
)

data class Song(
   val artistName: String,
   val collectionName: String,
   val artworkUrl60: String,
   val trackPrice: Double,
   val previewUrl: String,
   val trackName: String
)
