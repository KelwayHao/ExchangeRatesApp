package com.kelway.exchangeratesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.presentation.fragments.popular.PopularFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ExchangeRatesApplication.appComponent?.inject(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, PopularFragment.newInstance(), PopularFragment.TAG)
            .addToBackStack(PopularFragment.TAG)
            .commit()
    }
}