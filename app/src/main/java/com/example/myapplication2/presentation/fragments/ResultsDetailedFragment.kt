package com.example.myapplication2.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.databinding.FragmentResultsDetailedBinding
import com.example.myapplication2.domain.App
import com.example.myapplication2.presentation.viewModels.ResultsDetailedViewModel
import com.example.myapplication2.domain.ViewModelFactory
import javax.inject.Inject

class ResultsDetailedFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsDetailedFragment()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ResultsDetailedViewModel::class.java]
    }
/*
    private val viewModel: ResultsDetailedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val resultsDAO = (requireActivity().application as App).db.resultsDao()
                return ResultsDetailedViewModel(resultsDAO) as T
            }
        }
    }

 */
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
                }
            }

        }


    }

}