package com.example.myapplication2.presentation.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentResultsBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.presentation.adapter.ResultsAdapter
import com.example.myapplication2.presentation.viewModel.ResultsViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class ResultsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ResultsViewModel::class.java]
    }
    private lateinit var binding: FragmentResultsBinding
    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

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
                    Log.d(TAG, "aue $it")
                    var adapter = ResultsAdapter(
                        it
                    ) { num -> goToDetailedResults(num) }
                    binding.recyclerResults.adapter = adapter
                    if (it.isEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            context?.getString(R.string.toast_of_result),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, "here is null")
                    }
                }
            }
        }
    }

    private fun goToDetailedResults(id: Int) {
        val bundle: Bundle = Bundle()
        bundle.putInt("id", id)
        findNavController().navigate(R.id.action_resultsFragment_to_resultsDetailedFragment, bundle)
    }
}