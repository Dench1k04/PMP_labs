package com.example.Lab9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.Lab9.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: BooksDB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            BooksDB::class.java, "books_db"
        ).build()
        val bookDao = database.bookDao()

        binding.addBtn.setOnClickListener{
            val name = binding.name.text.toString()
            val author = binding.author.text.toString()
            val rating = binding.rating.text.toString().toInt()
            val book = Book(name = name, author = author, rating = rating)
            GlobalScope.launch {
                bookDao.insertAll(book)
            }
            Toast.makeText( applicationContext, "book added", Toast.LENGTH_LONG).show()
        }

        binding.allPlans.setOnClickListener{
            GlobalScope.launch {
                val books = bookDao.getAll()
                var booksInfo = ""
                books.forEach{
                    booksInfo += "${it.id}: ${it.name} ${it.author} ${it.rating}\n"
                }
                runOnUiThread{
                    binding.textView.text = booksInfo
                }
            }
        }

        binding.deleteButton.setOnClickListener{
            val bookId = binding.idText.text.toString().toIntOrNull()

            if (bookId == null || bookId < 0) {
                Toast.makeText(this, "Invalid index.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                bookDao.deleteById(bookId)

                GlobalScope.launch {
                    val books = bookDao.getAll()
                    var booksInfo = ""
                    books.forEach{
                        booksInfo += "${it.id}: ${it.name} ${it.author} ${it.rating}\n"
                    }
                    runOnUiThread{
                        binding.textView.text = booksInfo
                    }
                }
            }
        }

        GlobalScope.launch {
            val books = bookDao.getAll()
            var booksInfo = ""
            books.forEach{
                booksInfo += "${it.id}: ${it.name} ${it.author} ${it.rating}\n"
            }
            runOnUiThread{
                binding.textView.text = booksInfo
            }
        }
    }
}

