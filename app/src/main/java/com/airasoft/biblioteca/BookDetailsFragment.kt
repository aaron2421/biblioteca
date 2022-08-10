package com.airasoft.biblioteca

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.airasoft.biblioteca.database.Book
import com.airasoft.biblioteca.database.BookStoreDatabase
import com.airasoft.biblioteca.databinding.FragmentBookDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class BookDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailsBinding
    lateinit var title: String
    lateinit var author: String
    lateinit var editorial: String
    var year: Int = 0
    var price: Int = 0
    lateinit var category: String

    val args: BookDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BookStoreDatabase.getInstance(application).bookStoreDatabaseDao

        if (!args.isNewBook) {
            binding.titleForm.setText(args.bookData.bookTitle)
            binding.authorForm.setText(args.bookData.bookAuthor)
            binding.editorialForm.setText(args.bookData.bookEditorial)
            binding.yearForm.setText(args.bookData.bookYear.toString())
            binding.priceForm.setText(args.bookData.bookPrice.toString())
            binding.categoryForm.setText(args.bookData.bookCategory)
        }

        binding.saveBookButton.setOnClickListener {
            title = binding.titleForm.text.toString()
            author = binding.authorForm.text.toString()
            editorial = binding.editorialForm.text.toString()
            year = binding.yearForm.text.toString().toInt()
            price = binding.priceForm.text.toString().toInt()
            category = binding.categoryForm.text.toString()

            val book: Book

            if (args.isNewBook) {
                book = Book(title, author, editorial, year, price, category)
                dataSource.insert(book)
            } else {
                book = Book(title, author, editorial, year, price, category, args.bookData.bookId)
                dataSource.update(book)
            }

            it.findNavController().navigate(R.id.action_bookDetailsFragment_to_bookListFragment)
        }
        return binding.root
    }
}