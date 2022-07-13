package com.kelway.exchangeratesapp

import android.content.Context
import com.kelway.exchangeratesapp.data.di.DataModule
import com.kelway.exchangeratesapp.data.di.RetrofitModule
import com.kelway.exchangeratesapp.domain.di.DomainModule
import com.kelway.exchangeratesapp.presentation.MainActivity
import com.kelway.exchangeratesapp.presentation.fragments.favorites.FavoritesFragment
import com.kelway.exchangeratesapp.presentation.fragments.popular.PopularFragment
import com.kelway.exchangeratesapp.presentation.fragments.popular.recycler.PopularViewHolder
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class, DomainModule::class, RetrofitModule::class
    ]
)
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: PopularFragment)
    fun inject(target: PopularViewHolder)
    fun inject(target: FavoritesFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildContext(context: Context): Builder
        fun build(): AppComponent
    }
}