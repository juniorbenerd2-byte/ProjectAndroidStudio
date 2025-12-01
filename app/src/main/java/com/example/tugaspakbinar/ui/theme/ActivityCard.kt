package com.example.tugaspakbinar.ui.theme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.tugaspakbinar.R

class ActivityCard : AppCompatActivity() {

    // Deklarasi variabel
    private lateinit var cardprofil: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan layout ini ada
        setContentView(R.layout.cardprofil)

        // Inisialisasi View
        init()

        // Setup Listener
        cardprofil.setOnClickListener {
            Toast.makeText(this, "Menu Home Diklik!", Toast.LENGTH_SHORT).show()

            // Contoh pindah ke FormActivity (opsional, aktifkan jika perlu)
            // val intent = Intent(this, FormActivity::class.java)
            // startActivity(intent)
        }
    }

    private fun init() {

        // Pastikan ID ini sesuai dengan yang ada di cardprofil.xml
        cardprofil = findViewById(R.id.cardprofil)
    }
}
