package com.arctouch.codechallenge.network

import com.arctouch.codechallenge.model.Genre

object Cache {
    var genres = listOf<Genre>()
    fun cacheGenres(genres: List<Genre>?) {
        if (genres != null) {
            Cache.genres = genres
        }
    }
}
