package com.example.lab5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            showText("button1 OnClick")
        }

        binding.button2.setOnClickListener {
            showText("button2 OnClick")
        }

        binding.editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                showText("beforeTextChanged")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showText("onTextChanged")
            }
            override fun afterTextChanged(s: Editable?) {
                binding.textView.text = s.toString()
                showText("afterTextChanged")
            }
        })

        binding.editText1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showText("OnFocusChange focus")
            } else {
                showText("OnFocusChange unfocus")
            }
        }
    }

    private fun showText(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}