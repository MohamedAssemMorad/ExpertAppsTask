package com.example.myapplication.model.request

import com.example.myapplication.Constant
import com.example.myapplication.util.unique.IdentifierUtil
import java.util.*


abstract class BaseRequestFactory {
    abstract var baseRequestParam: BaseRequestParam
    private var defaultHeaders = HashMap<String, String>()

    init {
        defaultHeaders["locale"] = Locale.getDefault().language
        defaultHeaders["platform"] = "Android"
        defaultHeaders["aggregatorId"] = "SalesApp"
        defaultHeaders["IMEI"] = IdentifierUtil.getDeviceIMEI() ?: ""
//        defaultHeaders["Content-Type"] = "multipart/form-data;"
        defaultHeaders["Accept"] = "*/*"
    }

    open fun getUrl() = Constant.BASE_URL + getEndPoint()
    abstract fun getEndPoint(): String?

    fun getHeaderParam(isJson: Boolean = true): HashMap<String, String> {
        val headers = HashMap<String, String>()
        if (baseRequestParam.token != null)
            defaultHeaders["token"] = "Bearer ${baseRequestParam.token}"

        if (baseRequestParam.username != null)
            defaultHeaders["username"] = baseRequestParam.username.toString()

        if (baseRequestParam.loggedInUserId != null)
            defaultHeaders["loggedInUserId"] = baseRequestParam.loggedInUserId.toString()

        if (baseRequestParam.reqSecureKey != null)
            defaultHeaders["reqSecureKey"] = baseRequestParam.reqSecureKey.toString()

        if (isJson)
            defaultHeaders["Content-Type"] = "application/json"

        headers.putAll(defaultHeaders)
        headers.putAll(getCustomHeaders())
        return headers
    }

    open fun getCustomHeaders(): HashMap<String, String> = HashMap()


}