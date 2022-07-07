package com.example.myapplication.di

import com.example.myapplication.model.retrofit.Service
import com.efg.valu.sales.repository.local.BaseLocalRepo
import com.example.myapplication.dispatcher.movie.MovieDispatcher
import com.example.myapplication.dispatcher.movie.MovieDispatcherImpl
import com.example.myapplication.repository.local.BaseLocalRepoImpl
import com.example.myapplication.repository.remote.movie.MovieRemoteRepo
import com.example.myapplication.repository.remote.movie.MovieRemoteRepoImpl
import com.example.myapplication.viewmodel.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module


val localRepoModule = module {
    single<BaseLocalRepo> { BaseLocalRepoImpl() }
}
val networkModule = module {
    single { Service.getService() }
}

val movieModule = module {
    viewModel { MovieViewModel(get()) }
    single<MovieDispatcher> { MovieDispatcherImpl(get(), get()) }
    single<MovieRemoteRepo> { MovieRemoteRepoImpl(get()) }
}

