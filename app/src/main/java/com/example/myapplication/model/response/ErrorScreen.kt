package com.example.myapplication.model.response

import com.efg.valu.sales.model.response.BaseModel

data class ErrorScreen(
    val title: String,
    val message: String?,
    val responseCode: String?,
    val renderType: String,
    val errorPayload: BaseModel.ErrorPayload?,
    val endPoint: String?
) {
    constructor(
        message: String?,
        responseCode: String?,
        errorPayload: BaseModel.ErrorPayload?,
        endPoint: String?
    ) : this("", message, responseCode, "", errorPayload, endPoint)

    constructor(
        message: String?,
        responseCode: String?,
        errorPayload: BaseModel.ErrorPayload?
    ) : this("", message, responseCode, "", errorPayload, null)

    constructor(message: String?, responseCode: String?) : this(
        "",
        message,
        responseCode,
        "",
        null,
        null
    )
}