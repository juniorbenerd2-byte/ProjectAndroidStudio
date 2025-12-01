package com.example.tugaspakbinar

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder

class Activitykakulator : Activity() {

    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kakulator)

        tvDisplay = findViewById(R.id.number)


        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        val btnPlus = findViewById<Button>(R.id.btn_Plus)
        val btnMinus = findViewById<Button>(R.id.btn_Minus)
        val btnKali = findViewById<Button>(R.id.btnKali)
        val btnBagi = findViewById<Button>(R.id.btnBagi)
        val btnPersen = findViewById<Button>(R.id.btnPersen)
        val btnTitik = findViewById<Button>(R.id.btn_titik)

        val btnC = findViewById<Button>(R.id.btnC)
        val btnDel = findViewById<Button>(R.id.Del)
        val btnSamaDengan = findViewById<Button>(R.id.btnSamaDengan)



        fun appendText(str: String) {
            if (tvDisplay.text.toString() == "Error") {

                tvDisplay.text = ""
            }
            tvDisplay.append(str)
        }

        btn0.setOnClickListener { appendText("0") }
        btn1.setOnClickListener { appendText("1") }
        btn2.setOnClickListener { appendText("2") }
        btn3.setOnClickListener { appendText("3") }
        btn4.setOnClickListener { appendText("4") }
        btn5.setOnClickListener { appendText("5") }
        btn6.setOnClickListener { appendText("6") }
        btn7.setOnClickListener { appendText("7") }
        btn8.setOnClickListener { appendText("8") }
        btn9.setOnClickListener { appendText("9") }

        btnPlus.setOnClickListener { appendText("+") }
        btnMinus.setOnClickListener { appendText("-") }
        btnKali.setOnClickListener { appendText("*") }
        btnBagi.setOnClickListener { appendText("/") }
        btnPersen.setOnClickListener { appendText("%") }
        btnTitik.setOnClickListener { appendText(".") }

        // Tombol C (Clear)
        btnC.setOnClickListener {
            tvDisplay.text = ""
        }


        btnDel.setOnClickListener {
            val currentText = tvDisplay.text.toString()
            if (currentText.isNotEmpty()) {
                tvDisplay.text = currentText.dropLast(1)
            }
        }


        btnSamaDengan.setOnClickListener {
            try {
                val expressionStr = tvDisplay.text.toString()
                if (expressionStr.isNotEmpty()) {

                    val expression = ExpressionBuilder(expressionStr).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    // Tampilkan hasil (jika bulat, hilangkan desimal)
                    if (result == longResult.toDouble()) {
                        tvDisplay.text = longResult.toString()
                    } else {
                        tvDisplay.text = result.toString()
                    }
                }
            } catch (e: Exception) {
                // Tampilkan pesan error jika ekspresi tidak valid
                tvDisplay.text = "Error"
                Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}