package com.kelway.exchangeratesapp.presentation.spinner

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.kelway.exchangeratesapp.presentation.listener.SpinnerListener

abstract class Spinner {
    companion object {
        fun createSpinner(
            spinner: Spinner,
            context: Context,
            list: List<String>,
            spinnerListener: SpinnerListener
        ) {
            spinner.adapter =
                ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    spinnerListener.clickAction(list[p2])
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}