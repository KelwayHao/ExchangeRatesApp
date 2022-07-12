package com.kelway.exchangeratesapp.presentation.fragments.popular

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.FragmentPopularBinding
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import javax.inject.Inject

class PopularFragment : Fragment(R.layout.fragment_popular) {

    companion object {
        const val TAG = "PopularFragment"
        fun newInstance() = PopularFragment()
    }

    private val binding by viewBinding<FragmentPopularBinding>()

    @Inject
    lateinit var popularViewModel: PopularViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExchangeRatesApplication.appComponent?.inject(this)
        popularViewModel.loadData()
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun initView() {
        popularViewModel.rates.observe(viewLifecycleOwner) {
            Log.e("List", "${it.rates?.toString()}")
        }
    }
}