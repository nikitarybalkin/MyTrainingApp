package com.example.myapplication2.presentation.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentTrainBottomSheetBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.presentation.adapter.TrainBottomSheetAdapter
import com.example.myapplication2.presentation.adapter.TrainBottomSheetViewHolder
import com.example.myapplication2.presentation.viewModel.TrainViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = TrainBottomSheetFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TrainViewModel::class.java]
    }

    private lateinit var binding: FragmentTrainBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.d("LOL", "до шита долшло")
        val bundle = arguments?.getStringArray("listOfExercises")
        //Log.d("LOL", "бандл получен = ${bundle?.toList()}")
        val adapter = TrainBottomSheetAdapter(
            bundle?.toList(),
            {index1 -> setIndex(index1)}
        )
        Log.d("LOL", "vm in bottom ${viewModel}")
        binding.rvTrainBottomSheet.adapter = adapter


    }
    fun setIndex(index: Int) {
        viewModel.indexOfExercise.value = index
        Log.d("LOL", "setedIndex = ${viewModel.indexOfExercise.value}")
    }

}