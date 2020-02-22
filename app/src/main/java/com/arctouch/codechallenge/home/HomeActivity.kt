package com.arctouch.codechallenge.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arctouch.codechallenge.R

import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {
    private val upcomingAdapter = HomeAdapter()
    private val viewModel: MainViewModel by viewModels()


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
}
