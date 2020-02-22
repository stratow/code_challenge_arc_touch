package com.arctouch.codechallenge.extensions

import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.network.Cache


fun List<Movie>.updateGenres(): List<Movie>?{
    return this.map { movie ->
        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
    }
}