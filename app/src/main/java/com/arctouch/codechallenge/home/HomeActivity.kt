package com.arctouch.codechallenge.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.detail.MovieDetail
import com.arctouch.codechallenge.model.Movie
import com.google.gson.Gson

import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {
    private val upcomingAdapter = HomeAdapter(::movieClick)
    private val viewModel: MainViewModel by viewModels()

	companion object {
		const val MOVIE_DATA = "movie"
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        viewModel.genres.observe(this, Observer {
            viewModel.getMovies().observe(this, Observer {
                progressBar.visibility = View.GONE
                upcomingAdapter.submitList(it)
            })
        })
    }

    private fun initializeList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = upcomingAdapter
    }

	private fun movieClick(movie: Movie){
		val gson = Gson()
		val movieDetails = gson.toJson(movie)
		val newIntent = Intent(this, MovieDetail::class.java)
		newIntent.putExtra(MOVIE_DATA, movieDetails)
		startActivity(newIntent)
	}
}
