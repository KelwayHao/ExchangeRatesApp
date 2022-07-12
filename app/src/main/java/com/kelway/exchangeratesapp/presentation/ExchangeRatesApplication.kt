package com.kelway.exchangeratesapp.presentation

import android.app.Application
import com.kelway.exchangeratesapp.AppComponent
import com.kelway.exchangeratesapp.DaggerAppComponent

class ExchangeRatesApplication : Application() {
    companion object {
        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .buildContext(this)
            .build()
    }
}