package com.arctouch.codechallenge.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.extensions.buildBackdropUrl
import com.arctouch.codechallenge.extensions.buildPosterUrl
import com.arctouch.codechallenge.home.HomeActivity.Companion.MOVIE_DATA
import com.arctouch.codechallenge.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetail : AppCompatActivity() {

	private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
		sortIntent()
		title = movie.title
		setImages()
		setTexts()
    }

	private fun sortIntent(){
		val gson = Gson()
		movie = gson.fromJson(intent.getStringExtra(MOVIE_DATA), Movie::class.java)
	}

	private fun setImages(){
		Glide.with(this)
			.load(movie.backdropPath?.buildBackdropUrl())
			.apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
			.into(backdropImageView)

		Glide.with(this)
			.load(movie.posterPath?.buildPosterUrl())
			.apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
			.into(posterImageView)
	}

	private fun setTexts(){
		titleTextView.text = movie.title
		overviewTextView.text = movie.overview
		releaseDateTextView.text = movie.releaseDate

		genresTextView.text = movie.genres?.joinToString(separator = ", ") {it.name }
	}

}
