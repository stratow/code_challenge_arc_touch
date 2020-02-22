package com.arctouch.codechallenge.model

import com.google.gson.annotations.SerializedName


data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class UpcomingMoviesResponse(
        @SerializedName("page")
    val page: Int,
        @SerializedName("results")
    val results: List<Movie>,
        @SerializedName("total_pages")
    val totalPages: Int,
        @SerializedName("total_results")
    val totalResults: Int
)

data class Movie(
        @SerializedName("id")
    val id: Int,
        @SerializedName("title")
    val title: String,
        @SerializedName("overview")
    val overview: String?,
        val genres: List<Genre>?,
        @SerializedName("genre_ids")
    val genreIds: List<Int>?,
        @SerializedName("poster_path")
    val posterPath: String?,
        @SerializedName("backdrop_path")
    val backdropPath: String?,
        @SerializedName("release_date")
    val releaseDate: String?
)
