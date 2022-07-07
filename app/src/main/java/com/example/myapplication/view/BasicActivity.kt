package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.myapplication.model.response.ErrorScreen
import androidx.lifecycle.Observer
import com.example.myapplication.viewmodel.BaseViewModel


@SuppressLint("Registered")
abstract class BasicActivity : BaseActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel()?.showFullLoading?.observe(this, showFullLoading)
        getViewModel()?.errorDialog?.observe(this, showErrorUi)
    }

    private val showFullLoading = Observer<Boolean> {
        if (it)
            showLoading()
        else
            hideLoading()
    }

    private val showErrorUi = Observer<ErrorScreen> {
        showError(it)
    }

    override fun showError(error: ErrorScreen) {
//        Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
    }

    abstract fun getViewModel(): BaseViewModel?

    override fun onDestroy() {
        super.onDestroy()
        getViewModel()?.clear()
    }

    fun tryAgainPendingRequests() {
        getViewModel()?.retryPendingRequests()
    }
}




