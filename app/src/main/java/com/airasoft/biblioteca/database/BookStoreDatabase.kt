package com.airasoft.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookStoreDatabase: RoomDatabase() {

    abstract val bookStoreDatabaseDao: BookStoreDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BookStoreDatabase? = null

        fun getInstance(context: Context) : BookStoreDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookStoreDatabase::class.java,
                        "bookstore_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}