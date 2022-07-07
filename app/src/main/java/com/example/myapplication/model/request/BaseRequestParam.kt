package com.example.myapplication.model.request

import java.io.Serializable

open class BaseRequestParam(
    var token: String? = null, var reqSecureKey: String? = null, var loggedInUserId: String? = null, var username: String? = null
) : Serializable
