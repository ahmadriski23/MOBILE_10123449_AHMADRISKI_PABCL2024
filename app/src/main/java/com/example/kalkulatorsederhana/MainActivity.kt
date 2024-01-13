package com.example.kalkulatorsederhana
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.Locale
import android.content.Intent
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // deklarasi properti properti yang terdapat di xml
    private lateinit var inputMililiter: EditText
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mendapatkan id dari input mililiter serta result text view
        inputMililiter = findViewById(R.id.editTextMililiter)
        resultTextView = findViewById(R.id.textViewResult)
    }

    // memanggil halaman kalkulator
    fun kalkulatorPage(view: View) {
        val intent = Intent(this, KalkulatorActivity::class.java)
        startActivity(intent)
    }
    // memanggil halaman konversi gram ke cup
    fun gramToCupPage(view: View){
        val intent = Intent(this, GramToCupActivity::class.java)
        startActivity(intent)
    }

    // function convert ketika tombol konversi di click
    fun convertMililiterToOns(view:View){
        // mengkonversi teks  dari editText yang diberi nama input militer ke string
        val mililiterStr = inputMililiter.text.toString()
        // kondisi jika input mililiter kosong
        if (mililiterStr.isNotEmpty()){
            // diubah menjadi tipe double ketika tidak kosong
            val mililiter = mililiterStr.toDouble()
            // mengambil nilai mililiter ke ons cairan
            val onsCairan = mililiterToOnsCairan(mililiter)
            resultTextView.text = String.format(Locale.getDefault(), "%.2f mililiter = %.2f ons cairan", mililiter, onsCairan)
        } else {
            // * memunculkan pesan jika ada kesalaham
            Toast.makeText(this, "Masukkan jumlah mililiter!", Toast.LENGTH_SHORT).show()
        }
    }
    // function untuk mengkonversi mililiter ke ons cairan
    private fun mililiterToOnsCairan(mililiter:Double): Double{
        // mengambil nilai mililiter dan mengembalikan nilai ke ons cairan
        // 1 ons cairan = 29.5735 mililiter
        return mililiter / 29.5735
    }
}