package com.example.pamub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Hasil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val result = intent.getDoubleExtra("RESULT", 0.0)
        findViewById<TextView>(R.id.textViewResult).text = "Hasil: $result"
    }
}
