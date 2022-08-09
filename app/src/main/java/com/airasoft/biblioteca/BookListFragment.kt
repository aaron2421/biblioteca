package com.airasoft.biblioteca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.airasoft.biblioteca.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        binding.skipButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_bookListFragment_to_bookDetailsFragment)
        }

        return binding.root
    }
}