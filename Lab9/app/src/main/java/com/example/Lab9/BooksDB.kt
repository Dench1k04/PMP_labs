package com.example.Lab9

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BooksDB: RoomDatabase() {
    abstract fun bookDao(): BookDao
}

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "rating") val rating: Int?
)

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAll(): List<Book>

    @Insert
    fun insertAll(vararg books: Book)

    @Delete
    fun delete (book: Book)

    @Query("DELETE FROM books WHERE id = :booksId")
    fun deleteById(booksId: Int)
}
