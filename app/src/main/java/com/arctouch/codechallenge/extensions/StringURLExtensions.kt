package com.arctouch.codechallenge.extensions

import com.arctouch.codechallenge.BuildConfig


private val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

fun String.buildPosterUrl(): String{
    return POSTER_URL + this + "?api_key=" + BuildConfig.API_KEY
}

fun String.buildBackdropUrl(): String {
    return BACKDROP_URL + this + "?api_key=" + BuildConfig.API_KEY
}

