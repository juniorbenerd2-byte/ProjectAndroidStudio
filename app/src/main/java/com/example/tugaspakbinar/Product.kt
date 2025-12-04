package com.example.tugaspakbinar

import android.net.Uri

data class Product(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val price: Double,
    val imageUri: Uri? = null
)