package com.example.kalkulatorsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class GramToCupActivity : AppCompatActivity() {
    private lateinit var editTextGram: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gram_to_cup)
        // mendapatkan referensi id
        editTextGram= findViewById(R.id.editTextGram)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        // fungsi memanggil konversi gram ke cup pada button konversi
        buttonConvert.setOnClickListener {
            val gramText = editTextGram.text.toString()
            // kondisi jika gram text tidak kosong
            if(gramText.isNotEmpty()){
                val gram = gramText.toDouble()
                val cup = gramToCup(gram)
                // memasukkan angka dari gram dan cup ke dalam hasil teks
                textViewResult.text = String.format(Locale.getDefault(), "%.2f gram setara dengan %.2f cup",gram,cup)
            } else {
                Toast.makeText(this, "Masukkan jumlah gram terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // Fungsi untuk mengkonversi gram ke cup
    private fun gramToCup(gram: Double): Double {
        // 1 gram setara dengan 0.00422675 cup
        val cupPerGram = 0.00422675
        return gram * cupPerGram
    }
}