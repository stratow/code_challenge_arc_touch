package com.arctouch.codechallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.network.ApiClient
import com.arctouch.codechallenge.network.ApiService
import com.arctouch.codechallenge.network.Cache
import com.arctouch.codechallenge.network.UpcomingDataSourceFactory

import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    companion object {
        const val DEFAULT_LANGUAGE = "pt-BR"
    }

    private lateinit var upcomingLiveData: LiveData<PagedList<Movie>>
    private val apiService = ApiClient.getClient().create(ApiService::class.java)

    private val upcomingDataSourceFactory = UpcomingDataSourceFactory()

    val genres = liveData(Dispatchers.IO) {
        val retrievedGenres = apiService.genres(BuildConfig.API_KEY, DEFAULT_LANGUAGE)
        Cache.cacheGenres(retrievedGenres.genres)
        populateMovies()
        emit(retrievedGenres)
    }

    private fun populateMovies(){
        val movieConfig = PagedList.Config.Builder()
            .setPageSize(3)
            .setEnablePlaceholders(false)
            .build()
        upcomingLiveData = LivePagedListBuilder(upcomingDataSourceFactory, movieConfig).build()
    }

    fun getMovies():LiveData<PagedList<Movie>> = upcomingLiveData
}