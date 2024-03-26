package com.example.myapplication2.presentation.fragment

import android.content.ContentValues.TAG
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentDetailedBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.presentation.adapter.DetailedAdapter
import com.example.myapplication2.presentation.viewModel.DetailedViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
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
    }

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
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            viewModel.listOfExers.collect{
                    if (it.isNotEmpty()) {
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