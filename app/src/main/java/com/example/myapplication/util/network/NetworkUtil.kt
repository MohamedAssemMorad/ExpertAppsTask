package com.example.myapplication.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.myapplication.ExpertAppsApplication
import com.example.myapplication.R


object NetworkUtil {
    const val NO_INTERNET_CONNECTION_CODE = "NIC"
    val CLIENT_ERROR_MSG = ExpertAppsApplication.getContext()?.getString(R.string.error_client)
    val NETWORK_ERROR_MSG =
        ExpertAppsApplication.getContext()?.getString(R.string.no_internet_connection)
    val SERVER_ERROR_MSG =
        ExpertAppsApplication.getContext()?.getString(R.string.error_server)

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(): Boolean {
        var result = false
        val connectivityManager =
            ExpertAppsApplication.getContext()
                ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

}
