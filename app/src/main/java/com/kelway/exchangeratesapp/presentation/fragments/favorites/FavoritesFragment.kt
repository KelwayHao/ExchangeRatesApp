package com.kelway.exchangeratesapp.presentation.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.FragmentFavoritesBinding
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.fragments.favorites.recycler.FavoriteAdapter
import com.kelway.exchangeratesapp.presentation.listener.DeleteFavoriteClickListener
import com.kelway.exchangeratesapp.presentation.listener.SortListener
import com.kelway.exchangeratesapp.presentation.listener.SpinnerListener
import com.kelway.exchangeratesapp.presentation.sort.SortMenu
import com.kelway.exchangeratesapp.presentation.sort.SortType
import com.kelway.exchangeratesapp.presentation.spinner.Spinner
import com.kelway.exchangeratesapp.utils.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    companion object {
        const val TAG = "FavoritesFragment"
        fun newInstance() = FavoritesFragment()
    }

    private val binding by viewBinding<FragmentFavoritesBinding>()

    private val deleteFavoriteCurrency = object : DeleteFavoriteClickListener {
        override fun clickAction(favoriteCurrency: FavoriteCurrency) {
            favoriteViewModel.deleteFavorite(favoriteCurrency)
        }
    }

    private val sortListener = object : SortListener {
        override fun clickAction(sortType: SortType) {
            favoriteViewModel.sortCurrency(sortType)
        }
    }

    private val spinnerListener = object : SpinnerListener {
        override fun clickAction(q: String) {
            favoriteViewModel.setSearchValue(q)
        }
    }

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel
    private val adapter by lazy { FavoriteAdapter(deleteFavoriteCurrency) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExchangeRatesApplication.appComponent?.inject(this)
        initView()
    }

    private fun initView() {
        binding.sortImageButton.setOnClickListener { view: View ->
            SortMenu.showSortMenu(view, R.menu.sort_menu, sortListener, requireContext())
        }

        Spinner.createSpinner(
            binding.spinnerCurrency,
            requireContext(),
            Constants.BASE_QUERY_CURRENCY_LIST,
            spinnerListener
        )

        binding.recyclerFavorites.adapter = adapter

        favoriteViewModel.favoriteState
            .onEach {
                adapter.submitItem(it.rates)
            }
            .launchIn(lifecycleScope)
    }
}