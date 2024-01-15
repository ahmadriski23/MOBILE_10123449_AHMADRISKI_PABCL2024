package com.example.kalkulatorsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CupToGramActivity : AppCompatActivity() {
    // inisialisasi terlambat untuk variabel
    private lateinit var editTextCups: EditText
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cup_to_gram)
        editTextCups = findViewById(R.id.editTextCups)
        textViewResult = findViewById(R.id.textViewResult)
    }

    // fungsi konversi cup ke gram
    fun convertCupsToGram(view: View){
        // mendapatkan nilai yang dimasukkkan pengguna dalam edit text
        // mengubah edit text cup menjadi string dan mengkonversi string menjadi double
        val cups = editTextCups.text.toString()
        // kondisi jika cup tidak null
            if(cups.isNotEmpty()){
                val grams = cupToGram(cups.toDouble())
                textViewResult.text = "$cups cup = $grams gram"
            } else {
                // user mendapatkan pesan jika ada kesalahan dalam menginput
                Toast.makeText(this,"Masukkan jumlah cup terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
    }

    // fungsi menghitung cup ke gram
    private fun cupToGram(cups: Double): Double{
        // 1 cup air = 250 gram
        val gramPerCup = 250.0
        return cups * gramPerCup
    }

}