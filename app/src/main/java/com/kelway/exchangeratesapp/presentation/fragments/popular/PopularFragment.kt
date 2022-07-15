package com.kelway.exchangeratesapp.presentation.fragments.popular

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.FragmentPopularBinding
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.fragments.popular.recycler.PopularAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PopularFragment : Fragment(R.layout.fragment_popular) {

    companion object {
        const val TAG = "PopularFragment"
        fun newInstance() = PopularFragment()
    }

    private val binding by viewBinding<FragmentPopularBinding>()

    @Inject
    lateinit var popularViewModel: PopularViewModel
    private val adapter by lazy { PopularAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExchangeRatesApplication.appComponent?.inject(this)
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun initView() {
        binding.recyclerPopular.adapter = adapter
        popularViewModel.countState
            .onEach {
                adapter.submitItem(it.rates)
                Log.e("list", it.rates.toString())
            }
            .launchIn(lifecycleScope)
    }
}