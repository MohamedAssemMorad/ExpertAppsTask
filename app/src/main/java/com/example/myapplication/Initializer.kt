package com.example.myapplication

import android.content.Context
import com.example.myapplication.di.appComponents
import com.example.myapplication.di.networkComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



class Initializer(private val context: Context) {

    fun initKoin() {
        startKoin {

            androidContext(context)
            modules(
                appComponents +
                        networkComponent
            )
        }
    }
}