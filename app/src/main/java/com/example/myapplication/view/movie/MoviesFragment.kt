package com.example.myapplication.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentMovieBinding
import com.example.myapplication.view.BasicFragment
import com.example.myapplication.viewmodel.BaseViewModel
import com.example.myapplication.viewmodel.movie.MovieViewModel
import org.koin.android.ext.android.inject

class MoviesFragment : BasicFragment() {

    private val viewModel: MovieViewModel by inject()
    override fun getViewModel(): BaseViewModel = viewModel

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentMovieBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    override fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loadingView.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initUI() {
        viewModel.getMovieList()
    }

    override fun onBackPressed() = false
}