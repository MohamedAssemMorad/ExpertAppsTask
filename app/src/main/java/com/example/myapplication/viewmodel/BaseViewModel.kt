package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efg.valu.sales.model.response.BaseModel
import com.example.myapplication.model.response.ErrorScreen
import com.efg.valu.sales.model.response.ResponseException
import com.example.myapplication.dispatcher.BaseDispatcher
import com.example.myapplication.model.request.BaseRequestFactory
import com.example.myapplication.model.request.BaseRequestParam
import com.example.myapplication.model.request.RefreshToken
import kotlinx.coroutines.*
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashMap

abstract class BaseViewModel : ViewModel() {

    var errorDialog: MutableLiveData<ErrorScreen> = MutableLiveData()
    var showFullLoading: MutableLiveData<Boolean> = MutableLiveData()

    private var connectJobs: HashMap<String, Job?> = HashMap()

    private var connectJob: Job? = null
    private var refreshTokens: MutableMap<String, RefreshToken> = HashMap()

    fun fetchData(
        shouldConnected: Boolean = true,
        cash: Boolean, showLoading: Boolean, type: Type, requestFactory: BaseRequestFactory,
        proceedResponse: (t: Any?) -> Unit,

        ) {
        refreshTokens[type.toString()] =
            RefreshToken(shouldConnected,cash, showLoading, type, requestFactory, proceedResponse)
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            if (throwable is ResponseException)
                onError(ErrorScreen(throwable.message, throwable.responseCode,throwable.errorPayload,throwable.endPoint))
        }
//        onError(ErrorScreen("message", "400"))

        if (showLoading)
            showFullLoading.postValue(true)
        val connectJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = getDispatcher()?.fetchData(shouldConnected,cash, type, requestFactory)
            withContext(Dispatchers.Main) {
                proceedResponse(response)
                refreshTokens.remove(type.toString())

                if (showLoading)
                    showFullLoading.postValue(false)
            }
        }
        connectJobs[type.toString()] = connectJob
    }

//    private fun retryRefreshToken() {
//        val factory = SearchRequestFactoryNew()
//
//        fetchData(true,false, false, SearchResponse::class.java, factory) {
//            if (it is SearchResponse) {
//                searchResultsNew.value = it.metadata.results
//                retryPendingRequests()
//            }
//        }
//    }

    fun retryPendingRequests() {
        refreshTokens.values.forEach { refreshToken ->
            fetchData(
                refreshToken.shouldConnected,
                refreshToken.cache,
                refreshToken.showLoading,
                refreshToken.type,
                refreshToken.factory,
                refreshToken.proceedResponse
            )
        }
    }

    private fun onError(errorScreen: ErrorScreen) {
        Log.e("errorScreen", errorScreen.responseCode + "")
        if (errorScreen.responseCode == "404") {
//            retryRefreshToken()
        }
        errorDialog.postValue(errorScreen)
        showFullLoading.postValue(false)

    }

    override fun onCleared() {
        super.onCleared()
        connectJobs.values.forEach() {
            it?.cancel()
        }
    }

    fun clear() {
        onCleared()
    }

    abstract fun getDispatcher(): BaseDispatcher?

    fun getCashedObject(type: Type): Any? = getDispatcher()?.getCashedObject(type)

    fun saveObject(instance: BaseModel?, type: Type, lastModifiedDate: Long = Date().time) =
        getDispatcher()?.saveObject(instance, type, lastModifiedDate)

    fun saveObject(instance: BaseRequestParam?, type: Type, lastModifiedDate: Long = Date().time) =
        getDispatcher()?.saveObject(instance, type, lastModifiedDate)
}