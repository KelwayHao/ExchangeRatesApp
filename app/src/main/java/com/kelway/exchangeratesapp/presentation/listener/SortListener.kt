package com.kelway.exchangeratesapp.presentation.listener

import com.kelway.exchangeratesapp.presentation.sort.SortType

interface SortListener {
    fun clickAction(sortType: SortType)
}