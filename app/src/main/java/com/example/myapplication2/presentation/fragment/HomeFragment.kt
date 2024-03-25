package com.example.myapplication2.presentation.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentHomeBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.presentation.viewModel.HomeViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id: Int = 0
        viewModel.getLastTable()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.list.collect {
                it[0]?.let {
                        binding.tvHDate2.text = it.date
                        binding.tvHNameOfTrain2.text = it.nameOfTrain
                        //binding.tvHTime2.text = TimeConverter().convTime(it.time)
                        id = it.id-1
                }
            }
        }
        binding.table.setOnClickListener{
            val bundle: Bundle = Bundle()
            bundle.putInt("id", id)
            findNavController().navigate(R.id.action_homeFragment_to_resultsDetailedFragment, bundle)
        }



    }



}