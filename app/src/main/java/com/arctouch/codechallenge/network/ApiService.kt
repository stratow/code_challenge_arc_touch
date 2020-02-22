package com.arctouch.codechallenge.network

import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): GenreResponse

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String?
    ): Response<UpcomingMoviesResponse>

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Movie

    @GET("movie/{id}")
    suspend fun movie2(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<Movie>
}