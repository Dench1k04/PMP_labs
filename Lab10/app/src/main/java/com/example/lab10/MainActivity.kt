package com.example.lab10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab10.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUsers()

        binding.button.setOnClickListener {
            addUser()
        }
    }

    private fun getUsers(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            val response = apiInterface.getUser(1)
            binding.textView.text = response.body()?.name
        }
    }

    private fun addUser(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            try {
                val user = User(
                    id = 0,
                    name = binding.name.text.toString(),
                    username = binding.username.text.toString(),
                    email = binding.email.text.toString(),
                    phone = binding.phone.text.toString()
                )
                val response = apiInterface.addUser(user)
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(applicationContext, "User added! New user id: ${response.body()?.id}", Toast.LENGTH_SHORT).show()
                }
            } catch (Ex: Exception) {
                Toast.makeText(applicationContext, "error occured", Toast.LENGTH_SHORT).show()
            }
        }
    }
}