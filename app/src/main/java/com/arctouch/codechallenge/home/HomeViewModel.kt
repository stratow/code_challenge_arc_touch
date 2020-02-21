package com.arctouch.codechallenge.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.RetrofitSingleton
import com.arctouch.codechallenge.model.Movie
import kotlinx.coroutines.Dispatchers

class HomeViewModel : ViewModel() {

    val genres = liveData(Dispatchers.IO) {
        val retrievedGenres = RetrofitSingleton.api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
        Cache.cacheGenres(retrievedGenres.genres)
        emit(retrievedGenres)
    }

    val upcoming = liveData(Dispatchers.IO) {
        val upcomingMoviesResponse =
                RetrofitSingleton.api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)

        val moviesWithGenres = upcomingMoviesResponse.results.map { movie ->
            Log.d("movies", movie.genreIds.toString())
            Log.d("movies", Cache.genres.toString())
            movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(movie.id) == true })

        }

        emit(moviesWithGenres)
    }

}