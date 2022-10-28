package com.example.fruitapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fruitapp.databinding.FragmentFirstBinding
import com.example.fruitapp.fruitlist.FruitAdapter
import com.example.fruitapp.fruitlist.FruitListViewModel
import com.example.fruitapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: FruitListViewModel by viewModels()
    private lateinit var adapter: FruitAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        startObservers()
        /**
         * passing data:between fragments
         * 1 shared ViewModel
         * 2 Interfaces
         * 3 Bundle class
         */
        adapter = FruitAdapter(FruitAdapter.OnClickListener {
            Toast.makeText(activity, "${it.id}", Toast.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putInt("ID", it.id)
            //mode of navigation
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)

        })

        binding.recyclerView2.adapter= adapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(activity)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun startObservers() {
        viewModel.repository.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {//200
                    //pass the data to recyclerview
                    //Log.i("Data", it.data?.get(0)?.name.toString())
                    Log.i("Data", "" + (it.data))
                    //create recyclerview adapter
                    adapter.submitList(it.data)
                }
                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {
                    //progress Dialog
                }
            }
        }
    }
}