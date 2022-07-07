package com.efg.valu.sales.model.response


data class ResponseException(
    override var message: String? = "",
    var responseCode: String? = "",
    var endPoint: String? = "",
    var errorPayload: BaseModel.ErrorPayload? = null
) : Exception()