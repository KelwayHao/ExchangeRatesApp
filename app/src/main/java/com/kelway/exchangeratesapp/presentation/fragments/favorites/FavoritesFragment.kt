package com.kelway.exchangeratesapp.presentation.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.FragmentFavoritesBinding
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.fragments.favorites.recycler.FavoriteAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    companion object {
        const val TAG = "FavoritesFragment"
        fun newInstance() = FavoritesFragment()
    }

    private val binding by viewBinding<FragmentFavoritesBinding>()

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel
    private val adapter by lazy { FavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExchangeRatesApplication.appComponent?.inject(this)
        initView()
    }

    private fun initView() {
        binding.recyclerFavorites.adapter = adapter
        favoriteViewModel.favoriteState.onEach {
            adapter.submitItem(it)
        }
            .launchIn(lifecycleScope)
    }
}