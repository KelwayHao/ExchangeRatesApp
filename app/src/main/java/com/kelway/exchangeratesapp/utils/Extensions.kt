package com.kelway.exchangeratesapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.openFragment(idContainer: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
        .replace(idContainer, fragment, tag)
        .addToBackStack(tag)
        .commit()
}
