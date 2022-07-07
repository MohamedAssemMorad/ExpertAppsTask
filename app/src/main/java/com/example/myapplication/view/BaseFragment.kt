package com.example.myapplication.view

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onDestroyView() {
        super.onDestroyView()
        if (activity != null && activity is BaseActivity && requireActivity().supportFragmentManager
                .backStackEntryCount > 0
        ) {
            (activity as BaseActivity).notifyFragmentsAppearing()
        }
    }

    open fun onBackPressed()=true

    open fun didAppear() {}
}