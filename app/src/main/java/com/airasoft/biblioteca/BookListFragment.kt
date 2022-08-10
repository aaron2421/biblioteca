package com.airasoft.biblioteca

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airasoft.biblioteca.database.Book
import com.airasoft.biblioteca.database.BookStoreDatabase
import com.airasoft.biblioteca.databinding.FragmentBookListBinding

class BookListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentBookListBinding
    lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BookStoreDatabase.getInstance(application).bookStoreDatabaseDao

        val searchBar: SearchView = binding.searchBar
        searchBar.setOnQueryTextListener(this)

        adapter = BookAdapter(dataSource.getAllBooks(), dataSource)
        val recycler = binding.booksList
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        binding.addBookButton.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(true, Book("", "", "", 0))
            it.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onQueryTextChange(s: String?): Boolean {
        adapter.filtro(s)
        return false
    }
}