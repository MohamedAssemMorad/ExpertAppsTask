package com.example.myapplication.model.retrofit

import com.example.myapplication.model.response.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET
    suspend fun getMovieList(
        @Url url: String,
        @Query("page") pageNum: Int,
        @Query("sort_by") sortBy: String
    ): Response<MovieResponse>

}