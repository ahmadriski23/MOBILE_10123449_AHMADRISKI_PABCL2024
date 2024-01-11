package com.example.kalkulatorsederhana
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class KalkulatorActivity : AppCompatActivity() {
    // * variabel untuk menampilkan hasil perhitungan
    // private tidak bisa digunakan di kelas lain
    // late init digunakan untuk menandai properti yang akan di inisialisasi nanti atau terlambat
    // menghindari inisialisasi langsung saat deklarasi yang menyebabkan ketidaknyamanan atau kesalahan
    private lateinit var resultTextView: TextView
    // * variabel untuk menyimpan input angka berupa string
    private var currentInput = StringBuilder()
    // * variabel string untuk menyimpan operator yang dipilih user
    private var currentOperator: String? = null
    // * angka pertama yang dimasukkan user sebelum memilih operator
    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)
        // * mendapatkan id dari result text view
        resultTextView = findViewById(R.id.resultTextView)
    }
    // * fungsi menambahkan angka ke input ketika tombol angka ditekan
    fun onNumberClick(view: View){
        val button = view as Button
        currentInput.append(button.text)
        updateResult()
    }
    // * fungsi menyimpan operator dan angka pertama sebelum operator
    fun onOperatorClick(view: View){
        // * casting atau mengubah objek view ke button, untuk mengakses text dari tombol
        val button = view as Button
        try {
            currentOperator = button.text.toString()
            // * mengambil teks angka dari tombol yang di klik dan menambahkan ke current input
            operand1 = currentInput.toString().toDouble()
            currentInput.clear()
        } catch (e: NumberFormatException){
            // * menangani kesalahan konversi tipe data
            e.printStackTrace()
            currentInput.clear()
            // * memunculkan pesan jika ada kesalahan
            Toast.makeText(this,"Masukkan angka yang ingin dihitung!",Toast.LENGTH_SHORT).show()
        }
    }
    // * fungsi menambahkan titik ke inputan angka ketika ditekan
    fun onDotClick(view: View){
        // * memeriksa apakah sudah ada titik desimal menggunakan contains
        if (!currentInput.contains(".")){
            // jika belum, maka titik desimal akan ditambahkan ke input menggunakan append
            currentInput.append(".")
            // lalu tampilan akan diupdate atau diperbarui menggunakan fungsi updateResult()
            updateResult()
        }
    }
    // fungsi menampilkan hasil perhitungan ketika operator (=) ditekan
    fun onEqualClick(view: View){
        // memeriksa apakah angka, operator serta input tidak kosong
        if (operand1 != null && currentOperator != null && currentInput.isNotEmpty()){
            // mengkonversi nilai angka kedua menjadi tipe data Double
            val operand2 = currentInput.toString().toDouble()
            // melakukan operasi antara angka pertama serta kedua dan hasilnya disimpan ke result
            val result = when (currentOperator){
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> 0.0
            }
            // mereset tampilan ketika operasi perhitungan selesai
            currentInput.clear()
            // menambahkan hasil perhitungan result tadi ke current input untuk operasi lanjutan
            // jika user memilih untuk melanjutkan perhitungan
            currentInput.append(result)
            // memperbarui tampilan hasil dengan nilai terbaru dari currentInput
            updateResult()
            // memperbarui nilai angka pertama dengan hasil perhitungan
            operand1 = result
            // mengosongkan nilai operator setelah perhitungan selesai
            currentOperator = null
        }
    }

    // * fungsi menghapus semua input dan mereset semua angka
    fun onClearClick(view: View){
        // menghapus semua inputan angka yang ada
        currentInput.clear()
        operand1 = null
        currentOperator = null
        // memperbarui hasil tampilan ketika semua inputan sudah direset
        updateResult()
    }

    // * fungsi untuk memperbarui tampilan hasil dengan nilai yang ada
    private fun updateResult(){
        resultTextView.text = currentInput.toString()

    }
}