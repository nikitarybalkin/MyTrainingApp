package com.example.myapplication2.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentResultsBinding
import com.example.myapplication2.db.App
import com.example.myapplication2.viewModels.CreateTrainViewModel
import com.example.myapplication2.viewModels.ResultsViewModel
import kotlinx.coroutines.flow.collect

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private val viewModel: ResultsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val resultsDAO = (requireActivity().application as App).db.resultsDao()
                return ResultsViewModel(resultsDAO) as T
            }
        }
    }
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerResults.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getAll()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            viewModel.listOfExers?.collect {
                it?.let {
                    Log.d(TAG, "$it")
                    var adapter = ResultsAdapter(
                        it
                    ) { num -> goToDetailedResults(num) }
                    binding.recyclerResults.adapter = adapter
                    if (it.isEmpty()) Toast.makeText(
                        requireContext(),
                        context?.getString(R.string.toast_of_result),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    fun goToDetailedResults(id: Int) {
        var bundle: Bundle = Bundle()
        bundle.putInt("id", id)
        findNavController().navigate(R.id.action_resultsFragment_to_resultsDetailedFragment, bundle)
    }


}