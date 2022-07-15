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
        openFragment(R.id.frame, PopularFragment.newInstance(), PopularFragment.TAG)
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun initView() {
        binding.popularButton.setOnClickListener {
            openFragment(R.id.frame, PopularFragment.newInstance(), PopularFragment.TAG)
        }

        binding.favoritesButton.setOnClickListener {
            openFragment(R.id.frame, FavoritesFragment.newInstance(), FavoritesFragment.TAG)
        }
    }
}