package com.example.myapplication2.presentation.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentStartBinding
import com.example.myapplication2.presentation.viewModel.StartViewModel
import java.util.Calendar

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }
    private lateinit var binding: FragmentStartBinding
    private lateinit var viewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val time = Calendar.getInstance().time.toString()
        Log.d(TAG, "$time")
        binding.bCreate.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_createTrainFragment)
        }

        binding.bRes.setOnClickListener {

            findNavController().navigate(R.id.action_startFragment_to_resultsFragment)

        }

        binding.bMyTrains.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_myTrainingsFragment)
        }


    }


}