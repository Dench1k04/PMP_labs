package com.example.lab_8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_8.databinding.PhoneDetailsBinding

class PhoneDetailsActivity : AppCompatActivity() {
    private lateinit var binding: PhoneDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhoneDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneName = intent.getStringExtra("phoneName")
        binding.detailsButton.text = phoneName
    }
}
