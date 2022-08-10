package com.airasoft.biblioteca.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "bookstore_table")
data class Book(
    @ColumnInfo(name = "book_title")
    var bookTitle: String,
    @ColumnInfo(name = "book_author")
    var bookAuthor: String,
    @ColumnInfo(name = "book_editorial")
    var bookEditorial: String,
    @ColumnInfo(name = "book_year")
    var bookYear: Int,
    @PrimaryKey(autoGenerate = true)
    var bookId: Long = 0L
): Parcelable
