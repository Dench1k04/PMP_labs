package com.example.lab_8

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.recyclerViewVert.layoutManager = LinearLayoutManager(this)
        val phones = listOf(
            Pair("Apple", "https://hotline.ua/img/tx/409/4093641635.jpg"),
            Pair("Samsung", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmM7jehRRAOGGFBWZohzsVAweOF1MZGIjN-w1VeffUtQ&s"),
            Pair("Xiaomi", "https://hotline.ua/img/tx/364/3647270595.jpg"),
            Pair("Huawei", "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/admin-image/phones/nova-12i/list/green.png"),
            Pair("OnePlus", "https://hotline.ua/img/tx/372/3726035215.jpg"),
        )

        val adapter = PhoneAdapter(phones){ position: Int ->
            val intent = Intent(this, PhoneDetailsActivity::class.java)
            intent.putExtra("phoneName", phones[position].first)
            startActivity(intent)
        }

        binding.recyclerViewVert.adapter = adapter
        binding.recyclerViewHor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val phoneNames = listOf(
            "Burger",
            "Fries",
            "Chicken wings",
            "Hot-dog",
            "Tacos",
            "Pasta",
        )
        val adapterSecond = HorizontalViewAdapter(phoneNames)
        binding.recyclerViewHor.adapter = adapterSecond
    }
}