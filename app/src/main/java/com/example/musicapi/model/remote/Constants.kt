package com.example.musicapi.model.remote

// https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
// https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
// https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50

const val BASE_URL = "https://itunes.apple.com/"
const val ENDPOINT = "search"
const val PARAM_TERM = "term"