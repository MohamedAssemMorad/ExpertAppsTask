package com.example.myapplication.repository.remote

import com.efg.valu.sales.model.response.BaseModel
import com.example.myapplication.model.request.BaseRequestFactory
import retrofit2.Response

interface BaseRemoteRepo {
      suspend fun  fetchData(requestFactory: BaseRequestFactory): Response<out BaseModel>?
}