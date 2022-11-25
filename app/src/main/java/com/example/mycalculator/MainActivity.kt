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
        tvInput?.text = ""
        isDecimal = false
        isNumeric = true
    }

    fun onDecimal(view: View) {
        if (isNumeric && !isDecimal) {
            tvInput?.append(getString(R.string.decimal))
            isDecimal = true
            isNumeric = false
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (isNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                isNumeric = false
                isDecimal = false
            }
        }
    }

    fun isNumNegative(value: String) : Boolean {
        return value.startsWith("-")
    }

    fun hasOperator(value: String) : Boolean {
        return value.contains("+") || value.contains("-")
                || value.contains("*") || value.contains("/")
    }

    fun isOperatorAdded(value: String) : Boolean {
        if (isNumNegative(value) && !hasOperator(value.replace("-", "")))
            return false
        else
            return hasOperator(value)
    }

}