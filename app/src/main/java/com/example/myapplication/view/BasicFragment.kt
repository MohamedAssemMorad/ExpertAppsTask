package com.example.myapplication.view

import android.os.Bundle
import com.example.myapplication.model.response.ErrorScreen
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.util.network.NetworkUtil
import com.example.myapplication.viewmodel.BaseViewModel


abstract class BasicFragment : BaseFragment(), BaseView {

//    private lateinit var accountFailedBottomSheetFragment: ErrorMessageBottomSheetFragment


//    val options = navOptions {
//        anim {
//            enter = R.anim.slide_in_right
//            exit = R.anim.slide_out_left
//            popEnter = R.anim.slide_in_left
//            popExit = R.anim.slide_out_right
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel()?.showFullLoading?.observe(this, showFullLoading)
        getViewModel()?.errorDialog?.observe(this, showErrorUi)
    }

    override fun showError(
        error: ErrorScreen
    ) {

        showDefaultErrorDialog(error, {}, false)


    }

    fun showDefaultErrorDialog(
        error: ErrorScreen,
        onFinishClick: () -> Unit,
        defaultErrorDialogCancelable: Boolean
    ) {

        if (NetworkUtil.NO_INTERNET_CONNECTION_CODE == error.responseCode) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_connection),
                Toast.LENGTH_LONG
            ).show()
        } else {

            var title = ""
            var description = getString(R.string.error_server)
            if (error.errorPayload != null) {
                val errorObject = error.errorPayload
                if (!errorObject.title.isNullOrEmpty())
                    title = errorObject.title.toString()
                if (!errorObject.description.isNullOrEmpty())
                    description = errorObject.description.toString()
            }

//            accountFailedBottomSheetFragment =
//                ErrorMessageBottomSheetFragment(
//                    onFinishClick = {
//                        accountFailedBottomSheetFragment.dismiss()
//                        onFinishClick()
//                    },
//                    title,
//                    description,
//                    defaultErrorDialogCancelable
//                )
//            accountFailedBottomSheetFragment.show(
//                activity?.supportFragmentManager!!,
//                StatusBottomSheetFragment.TAG
//            )
        }
    }

    abstract fun getViewModel(): BaseViewModel?

    private val showFullLoading = Observer<Boolean> {
        if (it)
            showLoading()
        else
            hideLoading()
    }

    private val showErrorUi = Observer<ErrorScreen> { showError(it) }

    override fun onDestroy() {
        super.onDestroy()
        getViewModel()?.clear()
    }

    fun tryAgainPendingRequests() {
        getViewModel()?.retryPendingRequests()
    }
}