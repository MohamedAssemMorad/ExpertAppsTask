package com.example.myapplication.view

import com.example.myapplication.model.response.ErrorScreen

interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun showError(error: ErrorScreen)

}