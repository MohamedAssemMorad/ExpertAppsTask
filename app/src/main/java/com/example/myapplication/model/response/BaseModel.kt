package com.efg.valu.sales.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
abstract class BaseModel : Serializable {
    var message: String? = null
    var responseStatus: String? = null
    abstract fun getErrorValue(): ErrorPayload?
    open class ErrorPayload : Serializable{
        @field:SerializedName("title")
        val title: String? = null
        @field:SerializedName("description")
        val description: String? = null
    }
    open class Payload : Serializable
}

