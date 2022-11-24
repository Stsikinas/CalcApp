package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var isNumeric: Boolean = false
    private var isDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }


    fun onDigit(view: View) {
        if (!isNumeric)
            isNumeric = true
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        tvInput?.text = getString(R.string._0)
        isDecimal = false
        isNumeric = true
    }

    fun onDecimal(view: View) {
        if (isNumeric && !isDecimal) {
            tvInput?.append(getString(R.string.decimal))
            isDecimal = true
        }
    }

}