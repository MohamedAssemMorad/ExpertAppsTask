package com.example.myapplication.dispatcher

import com.example.myapplication.model.request.BaseRequestFactory
import com.example.myapplication.model.request.BaseRequestParam
import com.efg.valu.sales.model.response.BaseModel
import com.efg.valu.sales.model.response.ResponseException
import com.efg.valu.sales.repository.local.BaseLocalRepo
import com.example.myapplication.repository.remote.BaseRemoteRepo
import com.example.myapplication.util.network.NetworkUtil
import java.lang.reflect.Type
import java.util.*

interface BaseDispatcher {

    val localRepo: BaseLocalRepo
    val remoteRepo: BaseRemoteRepo


    suspend fun fetchData(
        shouldConnected: Boolean = true,
        cash: Boolean,
        type: Type,
        requestFactory: BaseRequestFactory
    ): Any? {
        var errorMessage: String? = null
        var responseCode: String? = null
        var errorPayload: BaseModel.ErrorPayload? = null
        val response = try {
            val isNetworkConnected = NetworkUtil.isNetworkAvailable()
            if (isNetworkConnected) {
                //todo add token to header
//                val loginResponseObj =
//                    (getCashedObject(LoginResponse::class.java) as LoginResponse?)
//
//                requestFactory.baseRequestParam.token = loginResponseObj?.payload?.account?.token
//                requestFactory.baseRequestParam.username =
//                    loginResponseObj?.payload?.account?.userName
//                requestFactory.baseRequestParam.loggedInUserId =
//                    loginResponseObj?.payload?.account?.loggedInUserId
//                requestFactory.baseRequestParam.reqSecureKey =
//                    loginResponseObj?.payload?.account?.reqSecureKey

                remoteRepo.fetchData(requestFactory)
            } else {
                errorMessage = NetworkUtil.NETWORK_ERROR_MSG
                responseCode = NetworkUtil.NO_INTERNET_CONNECTION_CODE
                null
            }
        } catch (ex: Exception) {
            errorMessage = NetworkUtil.CLIENT_ERROR_MSG
            responseCode = ""
            ex.printStackTrace()
            null
        }
        if (response != null && response.isSuccessful) {
            val body = response.body()

            if (body is BaseModel && body.responseStatus == "0") {
                val cache = response.body()
                if (cash) {
                    localRepo.saveObject(cache, type)
                }
                return cache
            } else {
                errorMessage = body?.message
                responseCode = body?.responseStatus
                errorPayload = body?.getErrorValue()
            }
        } else if (cash && !shouldConnected) {
            val cashed = localRepo.getCachedObject(type)
            if (cashed != null)
                return cashed
            if (errorMessage == null) {
                errorMessage = NetworkUtil.SERVER_ERROR_MSG
                responseCode = "" + response?.code()
            }
        } else {
            if (errorMessage == null) {
                errorMessage = NetworkUtil.SERVER_ERROR_MSG
                responseCode = "" + response?.code()
            }
        }

        throw ResponseException(
            message = errorMessage,
            responseCode = responseCode,
            endPoint = requestFactory.getEndPoint(), errorPayload = errorPayload
        )
    }

    fun getCashedObject(type: Type): Any? = localRepo.getCachedObject(type)

    fun saveObject(instance: BaseModel?, type: Type, lastModifiedDate: Long = Date().time) =
        localRepo.saveObject(instance, type, lastModifiedDate)

    fun saveObject(instance: BaseRequestParam?, type: Type, lastModifiedDate: Long = Date().time) =
        localRepo.saveObject(instance, type, lastModifiedDate)
}
