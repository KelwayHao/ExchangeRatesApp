package com.kelway.exchangeratesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ActivityMainBinding
import com.kelway.exchangeratesapp.presentation.fragments.favorites.FavoritesFragment
import com.kelway.exchangeratesapp.presentation.fragments.popular.PopularFragment
import com.kelway.exchangeratesapp.utils.openFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ExchangeRatesApplication.appComponent?.inject(this)
        openPopularFragment()
        initView()
    }

    private fun initView() {
        binding.popularButton.setOnClickListener {
            openPopularFragment()
        }
        binding.favoritesButton.setOnClickListener {
            openFavoritesFragment()
        }
    }

    private fun openPopularFragment() {
        openFragment(R.id.frame, PopularFragment.newInstance(), PopularFragment.TAG)
    }

    private fun openFavoritesFragment() {
        openFragment(R.id.frame, FavoritesFragment.newInstance(), FavoritesFragment.TAG)
    }
}