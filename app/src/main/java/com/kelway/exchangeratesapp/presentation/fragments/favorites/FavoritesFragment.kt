package com.kelway.exchangeratesapp.presentation.fragments.favorites

import androidx.fragment.app.Fragment
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.presentation.fragments.popular.PopularFragment

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    companion object {
        const val TAG = "FavoritesFragment"
        fun newInstance() = FavoritesFragment()
    }
}