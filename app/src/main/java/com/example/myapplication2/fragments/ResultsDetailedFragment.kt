package com.example.myapplication2.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.databinding.FragmentResultsDetailedBinding
import com.example.myapplication2.db.App
import com.example.myapplication2.viewModels.ResultsDetailedViewModel

class ResultsDetailedFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsDetailedFragment()
    }

    private val viewModel: ResultsDetailedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val resultsDAO = (requireActivity().application as App).db.resultsDao()
                return ResultsDetailedViewModel(resultsDAO) as T
            }
        }
    }
    private lateinit var binding: FragmentResultsDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsDetailedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

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
                }
            }

        }


    }

}