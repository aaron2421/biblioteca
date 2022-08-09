package com.airasoft.biblioteca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airasoft.biblioteca.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }

}