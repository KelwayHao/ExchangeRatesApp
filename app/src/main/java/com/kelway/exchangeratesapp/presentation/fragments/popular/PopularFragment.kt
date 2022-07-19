package com.kelway.exchangeratesapp.presentation.fragments.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.FragmentPopularBinding
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.fragments.popular.recycler.PopularAdapter
import com.kelway.exchangeratesapp.presentation.listener.AddFavoriteClickListener
import com.kelway.exchangeratesapp.presentation.listener.DeleteFavoriteClickListener
import com.kelway.exchangeratesapp.presentation.listener.SortListener
import com.kelway.exchangeratesapp.presentation.listener.SpinnerListener
import com.kelway.exchangeratesapp.presentation.sort.SortMenu
import com.kelway.exchangeratesapp.presentation.sort.SortType
import com.kelway.exchangeratesapp.presentation.spinner.Spinner
import com.kelway.exchangeratesapp.utils.Constants
import com.kelway.exchangeratesapp.utils.toFavoriteCurrency
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PopularFragment : Fragment(R.layout.fragment_popular) {

    companion object {
        const val TAG = "PopularFragment"
        fun newInstance() = PopularFragment()
    }

    private val binding by viewBinding<FragmentPopularBinding>()

    private val addFavoriteCurrency = object : AddFavoriteClickListener {
        override fun clickAction(currencyItem: CurrencyItem) {
            popularViewModel.addFavorite(currencyItem.toFavoriteCurrency())
        }
    }

    private val deleteFavoriteCurrency = object : DeleteFavoriteClickListener {
        override fun clickAction(favoriteCurrency: FavoriteCurrency) {
            popularViewModel.deleteFavorite(favoriteCurrency)
        }
    }

    private val sortListener = object : SortListener {
        override fun clickAction(sortType: SortType) {
            popularViewModel.sortCurrency(sortType)
        }
    }

    private val spinnerListener = object : SpinnerListener {
        override fun clickAction(query: String) {
            popularViewModel.setSearchValue(query)
        }
    }

    @Inject
    lateinit var popularViewModel: PopularViewModel
    private val adapter by lazy { PopularAdapter(addFavoriteCurrency, deleteFavoriteCurrency) }

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

        binding.recyclerPopular.adapter = adapter

        popularViewModel.countState
            .onEach { currency ->
                adapter.submitItem(currency.rates)
            }
            .launchIn(lifecycleScope)
    }
}