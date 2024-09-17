package com.example.pamub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var selectedEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.nilai1)
        val editText2 = findViewById<EditText>(R.id.nilai2)
        val radioButton = findViewById<RadioGroup>(R.id.radiobutton)
        val buttonHitung = findViewById<Button>(R.id.buttonhitung)

        // Set default selected EditText
        selectedEditText = editText1

        // Handle EditText focus
        editText1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) selectedEditText = editText1
        }
        editText2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) selectedEditText = editText2
        }

        // Handle number button clicks
        val buttonIds = listOf(R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9)
        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { button ->
                selectedEditText?.append((button as Button).text)
            }
        }

        // Handle the calculation
        buttonHitung.setOnClickListener {
            val num1 = editText1.text.toString().toDoubleOrNull()
            val num2 = editText2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (radioButton.checkedRadioButtonId) {
                R.id.radiotambah -> num1 + num2
                R.id.radiokurang -> num1 - num2
                R.id.radiokali -> num1 * num2
                R.id.radiobagi -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Pembagi tidak bisa nol", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    num1 / num2
                }
                else -> {
                    Toast.makeText(this, "Pilih operasi hitung", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val intent = Intent(this, Hasil::class.java).apply {
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }
    }
}
