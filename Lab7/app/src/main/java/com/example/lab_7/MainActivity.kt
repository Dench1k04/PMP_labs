package com.example.lab_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.lab_7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var name: String = ""
    private var surname: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.frame.id) as? NavHostFragment
        navHostFragment?.let { navFragment ->
            val firstFragment = navFragment.childFragmentManager.fragments.firstOrNull() as? FragmentFirst

            val bundle = Bundle().apply {
                putString("name", name)
                putString("surname", surname)
            }

            firstFragment?.let {
                firstFragment.childFragmentManager.setFragmentResult(
                    "main_data",
                    bundle
                )
            }
        }

        binding.button.setOnClickListener {
            val fragmentThird = FragmentThird()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragmentThird).addToBackStack(null).commit()
        }
    }
}