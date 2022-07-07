package com.example.myapplication.repository.remote.movie

import com.efg.valu.sales.model.response.BaseModel
import com.example.myapplication.model.request.BaseRequestFactory
import com.example.myapplication.model.request.movie.MovieFactory
import com.example.myapplication.model.retrofit.Api
import retrofit2.Response

open class MovieRemoteRepoImpl(var api: Api) : MovieRemoteRepo {
    override suspend fun fetchData(requestFactory: BaseRequestFactory): Response<out BaseModel>? {
        return when (requestFactory) {
            is MovieFactory -> {
                return api.getMovieList(requestFactory.getUrl(), 1, "vote_average.desc")
            }
            else -> null
        }
    }
}