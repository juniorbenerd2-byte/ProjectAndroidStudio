package com.example.tugaspakbinar

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class Suhu : AppCompatActivity() {

    private lateinit var inputSuhu: TextInputEditText
    private lateinit var spinnerSatuan: AutoCompleteTextView
    private lateinit var btnKonversi: Button
    
    // TextViews untuk label
    private lateinit var layoutCelcius: View
    private lateinit var layoutFahrenheit: View
    private lateinit var layoutReamur: View
    private lateinit var layoutKelvin: View

    // TextViews untuk hasil
    private lateinit var hasilCelcius: TextView
    private lateinit var hasilFahrenheit: TextView
    private lateinit var hasilReamur: TextView
    private lateinit var hasilKelvin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suhu)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupSpinner()
        setupButton()
    }

    private fun initViews() {
        inputSuhu = findViewById(R.id.inputSuhu)
        spinnerSatuan = findViewById(R.id.spinnerSatuan)
        btnKonversi = findViewById(R.id.btnKonversi)

        // Layout containers
        layoutCelcius = findViewById(R.id.layoutCelcius)
        layoutFahrenheit = findViewById(R.id.layoutFahrenheit)
        layoutReamur = findViewById(R.id.layoutReamur)
        layoutKelvin = findViewById(R.id.layoutKelvin)

        // Hasil TextViews
        hasilCelcius = findViewById(R.id.hasilCelcius)
        hasilFahrenheit = findViewById(R.id.hasilFahrenheit)
        hasilReamur = findViewById(R.id.hasilReamur)
        hasilKelvin = findViewById(R.id.hasilKelvin)
    }

    private fun setupSpinner() {
        val satuan = listOf("Celcius", "Fahrenheit", "Reamur", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, satuan)
        spinnerSatuan.setAdapter(adapter)
        
        // Default selection (opsional, tapi layout xml sudah set text default)
        // spinnerSatuan.setText(satuan[0], false)
    }

    private fun setupButton() {
        btnKonversi.setOnClickListener {
            hitungKonversi()
        }
    }

    private fun hitungKonversi() {
        val suhuStr = inputSuhu.text.toString()
        val satuanAsal = spinnerSatuan.text.toString()

        if (suhuStr.isEmpty()) {
            Toast.makeText(this, "Masukkan nilai suhu terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (satuanAsal.isEmpty()) {
             Toast.makeText(this, "Pilih satuan suhu", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val inputVal = suhuStr.toDouble()
            
            // Konversi input ke Celcius dulu sebagai base
            val celciusBase = when (satuanAsal) {
                "Celcius" -> inputVal
                "Fahrenheit" -> (inputVal - 32) * 5/9
                "Reamur" -> inputVal * 5/4
                "Kelvin" -> inputVal - 273.15
                else -> 0.0
            }

            // Hitung ke semua satuan
            val valCelcius = celciusBase
            val valFahrenheit = (celciusBase * 9/5) + 32
            val valReamur = celciusBase * 4/5
            val valKelvin = celciusBase + 273.15

            // Format angka
            val df = DecimalFormat("#.##")

            // Tampilkan hasil & sembunyikan yang dipilih sebagai input agar tidak redundan (opsional, user minta output lengkap)
            // User request: "outputnya juga lengkap", jadi kita tampilkan semua termasuk satuan asalnya untuk verifikasi
            
            hasilCelcius.text = "${df.format(valCelcius)} °C"
            hasilFahrenheit.text = "${df.format(valFahrenheit)} °F"
            hasilReamur.text = "${df.format(valReamur)} °R"
            hasilKelvin.text = "${df.format(valKelvin)} K"

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
        }
    }
}
