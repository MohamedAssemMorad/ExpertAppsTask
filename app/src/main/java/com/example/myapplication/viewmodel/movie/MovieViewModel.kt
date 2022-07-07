package com.example.myapplication.viewmodel.movie

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dispatcher.BaseDispatcher
import com.example.myapplication.dispatcher.movie.MovieDispatcher
import com.example.myapplication.model.request.movie.MovieFactory
import com.example.myapplication.model.response.movie.MovieResponse
import com.example.myapplication.model.response.movie.MovieResultsResponse
import com.example.myapplication.viewmodel.BaseViewModel

class MovieViewModel(private val movieDispatcher: MovieDispatcher) : BaseViewModel() {
    override fun getDispatcher(): BaseDispatcher = movieDispatcher
    val movieResults = MutableLiveData<List<MovieResultsResponse>>()

    fun getMovieList() {
        fetchData(
            shouldConnected = true,
            cash = false,
            showLoading = true,
            type = MovieResponse::class.java,
            requestFactory = MovieFactory()
        ) {
            if (it is MovieResponse) {
                movieResults.value = it.results ?: ArrayList()
            }
        }

    }
}