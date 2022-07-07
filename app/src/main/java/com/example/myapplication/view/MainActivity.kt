package com.example.myapplication.view

import android.os.Bundle
import com.example.myapplication.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment =
            supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(0)
        if (fragment is BaseFragment) {
            if (fragment.onBackPressed())
                return super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun getContainerId(): Int {
        return R.id.layout_container
    }
}