    package com.example.pamub

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText1 = findViewById<EditText>(R.id.nilai1)
        val editText2 = findViewById<EditText>(R.id.nilai2)
        val radioButton = findViewById<RadioGroup>(R.id.radiobutton)
        val button = findViewById<Button>(R.id.buttonhitung)

        button.setOnClickListener{
            val num1 = editText1.text.toString().toDoubleOrNull()
            val num2 = editText1.text.toString().toDoubleOrNull()

            if(num1 == null || num2 == null) {
                Toast.makeText(this, "masukkan angka yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when(radioButton.checkedRadioButtonId){
                R.id.radiotambah -> num1 + num2
                R.id.radiokurang -> num1 - num2
                R.id.radiokali -> num1 * num2
                R.id.radiobagi -> {
                    if(num2 == 0.0) {
                        Toast.makeText(this, "pembagi tidak bisa nol", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    num1 / num2
                }
                else -> {
                    Toast.makeText(this, "pilih operasi hitung", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val intent = Intent(this, Result::class.java).apply{
                putExtra("RESULT", result)
            }
            startActivity(intent)
        }
    }
}