package com.example.myapplication2.fragments

import android.content.ContentValues.TAG
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentDetailedBinding
import com.example.myapplication2.db.App
import com.example.myapplication2.viewModels.CreateTrainViewModel
import com.example.myapplication2.viewModels.DetailedViewModel
import com.example.myapplication2.viewModels.ViewModelFactory
import javax.inject.Inject

class DetailedFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedFragment()
    }

    private lateinit var binding: FragmentDetailedBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailedViewModel::class.java]
    }/*
    private val viewModel: DetailedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val trainingDAO = (requireActivity().application as App).db.exerciseDao()
                return DetailedViewModel(trainingDAO) as T
            }
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerDetailed.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getList()
        var bundle = arguments?.getInt("aue")
        var bundle2 = Bundle()
        //Log.d(TAG, "bundleInDetailed = $bundle")
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            viewModel.listOfExers.collect{
                    if (it.isNotEmpty() && it != null) {
                        bundle?.let { bundle ->
                            val adapter = it[bundle].exercises?.let { it1 -> DetailedAdapter(it1) }
                            binding.recyclerDetailed.adapter = adapter
                            bundle2.putInt("NameOfList", bundle)
                        }

                    }
            }

        }
        binding.bStartTrain.setOnClickListener {

            Log.d(TAG, "bundle2 = $bundle2")
            findNavController().navigate(R.id.action_detailedFragment_to_trainFragment, bundle2)

        }

    }

}