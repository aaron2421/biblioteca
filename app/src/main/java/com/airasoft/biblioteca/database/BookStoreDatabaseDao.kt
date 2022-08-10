package com.airasoft.biblioteca.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookStoreDatabaseDao {

    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Query("SELECT * FROM bookstore_table ORDER BY book_title")
    fun getAllBooks(): List<Book>

    @Query("DELETE FROM bookstore_table WHERE book_title = :bookTitle")
    fun deleteBook(bookTitle: String)
}