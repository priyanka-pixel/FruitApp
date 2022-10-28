package com.example.fruitapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fruitapp.databinding.FragmentThirdBinding
import com.example.fruitapp.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ThirdFragment() : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val viewModel: SearchViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            val searchEntry = binding.searchEntry.text.toString()
            viewModel.fetchFruits(searchEntry)
            viewModel.fruitLiveData.observe(viewLifecycleOwner) {
                binding.textView.text = it.name
                binding.textView6.text = it.family
                binding.textView3.text = it.genus
                binding.textView4.text = it.order
                Log.i("FruitItem", it.name)
            }
        }
        viewModel.fetchFruits("Apple")
        viewModel.fruitLiveData.observe(viewLifecycleOwner) {
            Log.i("DataFruits", it.name)
        }
    } }


