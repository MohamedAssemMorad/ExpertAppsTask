package com.example.myapplication.util.unique

import android.annotation.SuppressLint
import android.provider.Settings
import com.example.myapplication.ExpertAppsApplication


object IdentifierUtil {

    @SuppressLint("HardwareIds")
    fun getDeviceIMEI(): String? {

        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            imei = Settings.Secure.getString(
//                ValuApplication.getContext()!!.contentResolver,
//                Settings.Secure.ANDROID_ID
//            )
//        } else {
//            Settings.Secure.getString(ValuApplication.getContext()?.contentResolver, Settings.Secure.ANDROID_ID)
//            val mTelephony = ValuApplication.getContext()!!
//                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//            imei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                mTelephony.meid
//            } else {
//                mTelephony.deviceId
//            }
//            if (imei == null) {
//                imei = Settings.Secure.getString(
//                    ValuApplication.getContext()!!.contentResolver,
//                    Settings.Secure.ANDROID_ID
//                )
//            }
//        }

        return Settings.Secure.getString(
            ExpertAppsApplication.getContext()!!.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}