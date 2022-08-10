package com.airasoft.biblioteca

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airasoft.biblioteca.database.Book
import com.airasoft.biblioteca.database.BookStoreDatabase
import com.airasoft.biblioteca.database.BookStoreDatabaseDao
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.function.Predicate
import java.util.stream.Stream
import kotlin.streams.toList

class BookAdapter(private var books: List<Book>, dao: BookStoreDatabaseDao): RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private val dataSource = dao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = books[position]
        holder.bookTitle.text = item.bookTitle
        holder.deleteBookButton.setOnClickListener {
            MaterialAlertDialogBuilder(it.context)
                .setMessage("¿Esta seguro que desea borrar el libro?")
                .setNegativeButton("No") { _, _ -> }
                .setPositiveButton("Si") { _, _ ->
                    dataSource.deleteBook(item.bookTitle)
                    Snackbar.make(it, "Libro eliminado.", Snackbar.LENGTH_SHORT).show()
                    it.findNavController().navigate(R.id.action_bookListFragment_self)
                }
                .show()
        }
        holder.updateBookButton.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(false, Book(item.bookTitle, item.bookAuthor, item.bookEditorial, item.bookYear, item.bookId))
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = books.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.bookTitleTextView)
        val deleteBookButton: Button = itemView.findViewById(R.id.deleteBookButton)
        val updateBookButton: Button = itemView.findViewById(R.id.updateBookButton)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun filtro(query: String?) {
        if(query!!.isEmpty()) {
            books = emptyList()
            books = dataSource.getAllBooks()
        } else {
            val filteredData: Stream<Book> = books.stream()
                .filter { i -> i.bookTitle.lowercase().contains(query.lowercase()) ||
                               i.bookAuthor.lowercase().contains(query.lowercase()) ||
                               i.bookEditorial.lowercase().contains(query.lowercase()) ||
                               i.bookYear.toString().contains(query) }
            books = emptyList()
            books = filteredData.toList()
        }
    }
}

