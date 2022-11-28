package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var isDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }


    fun onDigit(view: View) {
        if (!lastNumeric)
            lastNumeric = true
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        isDecimal = false
        lastNumeric = true
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !isDecimal) {
            tvInput?.append(getString(R.string.decimal))
            isDecimal = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                isDecimal = false
            }
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            val operation: String
            val splitValues: Array<String>
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                operation = if (tvValue.contains("-")) {
                    "-"

                } else if (tvValue.contains("+")) {
                    "+"

                }  else if (tvValue.contains("/")) {
                    "/"
                } else {
                    "*"
                }
                splitValues = tvValue.split(operation).toTypedArray()

                var firstValue = splitValues[0]
                val secondValue = splitValues[1]

                if (prefix.isNotEmpty())
                    firstValue = prefix + firstValue

                var result = ""

                when(operation) {
                    "+" -> result = (roundValue(firstValue)
                            + roundValue(secondValue)).toString()
                    "-" -> result = (roundValue(firstValue)
                            - roundValue(secondValue)).toString()
                    "*" -> result = (roundValue(firstValue)
                            * roundValue(secondValue)).toString()
                    "/" -> result = (roundValue(firstValue)
                            / roundValue(secondValue)).toString()
                }

                tvInput?.text = removeZeroAfterDot(roundValue(result).toString())

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isNumNegative(value: String) : Boolean {
        return value.startsWith("-")
    }

    private fun hasOperator(value: String) : Boolean {
        return value.contains("+") || value.contains("-")
                || value.contains("*") || value.contains("/")
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (isNumNegative(value) && !hasOperator(value.substring(1)))
            false
        else
            hasOperator(value)
    }

    private fun removeZeroAfterDot(value: String) : String {
        if (value.endsWith(".0"))
            return value.split(".")[0]
        return value
    }

        private fun roundValue(value: String) : Double {
        return String.format("%.3f", value.toDoubleOrNull()).toDouble()
    }

}