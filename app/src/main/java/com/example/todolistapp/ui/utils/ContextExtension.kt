package com.example.todolistapp.ui.utils

import android.content.Context
import android.widget.Toast

fun Context.Toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}