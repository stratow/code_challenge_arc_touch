package com.arctouch.codechallenge.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        observeData()
    }

    private fun observeData(){
        getGenres()
    }

    private fun getGenres() {
        viewModel.genres.observe(this, Observer {
            getUpcomingMovies()
        })
    }

    private fun getUpcomingMovies(){
        viewModel.upcoming.observe(this, Observer {
                recyclerView.adapter = HomeAdapter(it)
                progressBar.visibility = View.GONE
        })
    }
}
