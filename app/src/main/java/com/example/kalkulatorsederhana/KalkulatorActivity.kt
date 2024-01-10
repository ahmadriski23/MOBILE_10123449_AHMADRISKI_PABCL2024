package com.example.kalkulatorsederhana
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class KalkulatorActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        currentInput.append(button.text)
        updateResult()
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        try {
            currentOperator = button.text.toString()
            operand1 = currentInput.toString().toDouble()
            currentInput.clear()
        } catch (e: NumberFormatException) {
            // Tangani kesalahan konversi tipe data di sini
            e.printStackTrace()
            currentInput.clear()
            resultTextView.text = "Oopppss!!, Error"
        }
    }

    fun onDotClick(view: View) {
        if (!currentInput.contains(".")) {
            currentInput.append(".")
            updateResult()
        }
    }

    fun onEqualClick(view: View) {
        if (operand1 != null && currentOperator != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> 0.0
            }
            currentInput.clear()
            currentInput.append(result)
            updateResult()
            operand1 = result
            currentOperator = null
        }
    }

    fun onClearClick(view: View) {
        currentInput.clear()
        operand1 = null
        currentOperator = null
        updateResult()
    }

    private fun updateResult() {
        resultTextView.text = currentInput.toString()
    }
}