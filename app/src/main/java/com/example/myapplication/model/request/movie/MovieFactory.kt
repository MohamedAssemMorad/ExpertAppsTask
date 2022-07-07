package com.example.myapplication.model.request.movie

import com.example.myapplication.model.request.BaseRequestFactory
import com.example.myapplication.model.request.BaseRequestParam
import com.example.myapplication.model.retrofit.EndPoints

class MovieFactory : BaseRequestFactory() {
    override fun getEndPoint(): String = EndPoints.MOVIE_URL
    override var baseRequestParam = BaseRequestParam()
}