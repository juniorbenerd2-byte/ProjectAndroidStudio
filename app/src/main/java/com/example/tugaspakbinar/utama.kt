package com.example.tugaspakbinar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspakbinar.ui.theme.ActivityCard

class utama : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_utama)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi CardView
        val cardKalkulator = findViewById<CardView>(R.id.cardKalkulator)
        val cardMenu = findViewById<CardView>(R.id.cardMenu)
        val cardSuhu = findViewById<CardView>(R.id.cardSuhu)
        val cardProfile = findViewById<CardView>(R.id.cardProfile)
        val cardLogin = findViewById<CardView>(R.id.cardLogin)
        val cardExit = findViewById<CardView>(R.id.cardExit)

        // Intent ke ActivityKalkulator
        cardKalkulator.setOnClickListener {
            val intent = Intent(this, Activitykakulator::class.java)
            startActivity(intent)
        }

        // Intent ke Menu
        cardMenu.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        // Intent ke Suhu
        cardSuhu.setOnClickListener {
            val intent = Intent(this, Suhu::class.java)
            startActivity(intent)
        }

        // Intent ke ActivityCard (Profile)
        cardProfile.setOnClickListener {
            val intent = Intent(this, ActivityCard::class.java)
            startActivity(intent)
        }

        // Intent ke ActivityFormLogin (Form Login)
        cardLogin.setOnClickListener {
            val intent = Intent(this, ActivityFormLogin::class.java)
            startActivity(intent)
        }

        // Fitur Exit
        cardExit.setOnClickListener {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                finishAffinity() // Menutup semua activity dan keluar aplikasi
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
