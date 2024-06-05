package com.example.myapplication2.presentation.fragment

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.R.string
import com.example.myapplication2.databinding.FragmentResultsDetailedBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.presentation.adapter.ResultsDetailedAdapter
import com.example.myapplication2.presentation.utils.TimeConverter
import com.example.myapplication2.presentation.viewModel.ResultsDetailedViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class ResultsDetailedFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ResultsDetailedViewModel::class.java]
    }
    private lateinit var binding: FragmentResultsDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsDetailedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerResultsDetailed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getAll()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        var bundle = arguments?.getInt("id")
            viewModel.resList.collect {
                it?.let {
                    var adapter = ResultsDetailedAdapter(it[bundle!!])
                    binding.recyclerResultsDetailed.adapter = adapter
                    binding.tvNameOfTrain.text = "${getString(string.name_of_train)}: ${it[bundle]!!.nameOfTrain}"
                    binding.tvDate.text = "${getString(string.date)}: ${it[bundle]!!.date}"
                    binding.tvTime.text = "${getString(string.time)}: ${TimeConverter(requireContext()).convTime(it[bundle]!!.time)}"
                }
            }
        }
    }
}