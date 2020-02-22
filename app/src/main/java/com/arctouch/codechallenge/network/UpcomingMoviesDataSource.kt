package com.arctouch.codechallenge.network

import androidx.paging.PageKeyedDataSource
import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.extensions.updateGenres
import com.arctouch.codechallenge.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class UpcomingMoviesDataSource(coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Long, Movie>() {

    companion object {
        const val DEFAULT_LANGUAGE = "pt-BR"
    }

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)
    private val apiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Movie>) {
        scope.launch {
            try {
                val upcomingMoviesResponse =
                    apiService.upcomingMovies(BuildConfig.API_KEY, DEFAULT_LANGUAGE, 1, null)

                when {
                    upcomingMoviesResponse.isSuccessful -> {
                        val moviesWithGenres =
                            upcomingMoviesResponse.body()?.results?.updateGenres()
                        callback.onResult(moviesWithGenres ?: listOf(), null, 2)
                    }
                }


            } catch (e: Exception) {
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        scope.launch {
            try {
                val upcomingMoviesResponse =
                    apiService.upcomingMovies(BuildConfig.API_KEY, DEFAULT_LANGUAGE, params.key,null)

                when {
                    upcomingMoviesResponse.isSuccessful -> {
                        val moviesWithGenres =
                            upcomingMoviesResponse.body()?.results?.updateGenres()
                        callback.onResult(moviesWithGenres ?: listOf(), params.key + 1)
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}