package com.kelway.exchangeratesapp.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
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
        binding.sortImageButton.setOnClickListener { view: View ->
            showSortMenu(view, R.menu.sort_menu)
        }
        binding.popularButton.setOnClickListener {
            openPopularFragment()
        }
        binding.favoritesButton.setOnClickListener {
            openFavoritesFragment()
        }
    }

    private fun showSortMenu(view: View, menuRes: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menuRes, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.sortByAlphabet -> {

                }
                R.id.sortByAlphabetReverse -> {

                }
                R.id.sortByPriceLowest -> {

                }
                R.id.sortByPriceHighest -> {

                }

            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()

        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenu)
        menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
            .invoke(menu,true)
    }

    private fun openPopularFragment() {
        openFragment(R.id.frame, PopularFragment.newInstance(), PopularFragment.TAG)
    }

    private fun openFavoritesFragment() {
        openFragment(R.id.frame, FavoritesFragment.newInstance(), FavoritesFragment.TAG)
    }
}