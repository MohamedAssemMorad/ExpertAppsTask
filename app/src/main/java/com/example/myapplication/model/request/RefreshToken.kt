package com.example.myapplication.model.request

import com.example.myapplication.model.request.BaseRequestFactory
import java.lang.reflect.Type

data class RefreshToken(
    var shouldConnected: Boolean = false,
    var cache: Boolean = false,
    var showLoading: Boolean,
    var type: Type,
    var factory: BaseRequestFactory,
    var proceedResponse: (t: Any?) -> Unit
)