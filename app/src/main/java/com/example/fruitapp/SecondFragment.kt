package com.example.fruitapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fruitapp.databinding.FragmentSecondBinding
import com.example.fruitapp.models.FruitsItem
import com.example.fruitapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val viewModel: FruitDetailViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
        arguments?.getInt("ID").let {
            viewModel.startDetailsCall((it!!))
        }
        startObserversForDetails()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun bindDetailsData(fruit: FruitsItem) {
        binding.textView.text = fruit.name
        binding.textView2.text = fruit.family
        binding.textView3.text = fruit.order
        binding.textView4.text = fruit.genus
    }
    fun startObserversForDetails() {
        viewModel.fruit.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("Data", "" + (it.data))
                    bindDetailsData(it.data!!)
                }
                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {
                }
            }
        }
    }
}
