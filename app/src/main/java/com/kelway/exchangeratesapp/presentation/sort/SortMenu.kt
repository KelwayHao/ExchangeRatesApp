package com.kelway.exchangeratesapp.presentation.sort

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.presentation.listener.SortListener

abstract class SortMenu() {
    companion object {
        fun showSortMenu(view: View, menuRes: Int, sortListener: SortListener, context: Context) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(menuRes, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.sortByAlphabet -> {
                        sortListener.clickAction(SortType.NAME_UP)
                    }
                    R.id.sortByAlphabetReverse -> {
                        sortListener.clickAction(SortType.NAME_DOWN)
                    }
                    R.id.sortByPriceLowest -> {
                        sortListener.clickAction(SortType.VALUE_UP)
                    }
                    R.id.sortByPriceHighest -> {
                        sortListener.clickAction(SortType.VALUE_DOWN)
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
    }
}