package com.arctouch.codechallenge.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arctouch.codechallenge.model.Movie

import kotlinx.coroutines.Dispatchers

class UpcomingDataSourceFactory : DataSource.Factory<Long, Movie>() {
    private val upcomingDataSourceLiveData = MutableLiveData<UpcomingMoviesDataSource>()

    override fun create(): DataSource<Long, Movie> {
        val upcomingDataSource = UpcomingMoviesDataSource(Dispatchers.IO)
        upcomingDataSourceLiveData.postValue(upcomingDataSource)
        return upcomingDataSource
    }
}