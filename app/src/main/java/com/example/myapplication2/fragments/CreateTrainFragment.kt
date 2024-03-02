package com.example.myapplication2.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
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
import com.example.myapplication2.Exercise
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentCreateTrainBinding
import com.example.myapplication2.databinding.FragmentStartBinding
import com.example.myapplication2.db.App
import com.example.myapplication2.db.TrainingEntity
import com.example.myapplication2.viewModels.CreateTrainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateTrainFragment : Fragment() {

    companion object {
        fun newInstance() = CreateTrainFragment()
    }

    private lateinit var binding: FragmentCreateTrainBinding
    private val viewModel: CreateTrainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val trainingDAO = (requireActivity().application as App).db.exerciseDao()
                return CreateTrainViewModel(trainingDAO) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTrainBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lExes = mutableListOf<String>()
        var nameOfTrain: String? = context?.getString(R.string.no_name)
        binding.b1.text = context?.getString(R.string.next_exercise)
        binding.b2.text = context?.getString(R.string.finish_training)



        binding.b1.setOnClickListener() {

            if (binding.edNameOfTrain.text.isNotEmpty() && binding.edNameOfExercise.text.isNotEmpty()) {
                nameOfTrain = binding.edNameOfTrain.text.toString()
                //Log.d(TAG, "nameFTR = ${binding.edNameOfTrain.text.toString()}")
                //l_Exes?.set(l_Exes.size - 1, binding.edNameOfExercise.text.toString())
                lExes?.add(binding.edNameOfExercise.text.toString())
                //Log.d(TAG, "l_exes = $l_Exes")
                //l_Exes.add((binding.edNameOfExercise.text.toString()))
                binding.edNameOfExercise.text.clear()

            }

        }

        binding.b2.setOnClickListener() {
            viewModel.insert(TrainingEntity(nameOfTrain!!, lExes))
            findNavController().navigate(R.id.action_createTrainFragment_to_startFragment)

        }

        /*viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            var a : List<TrainingEntity>
            viewModel.list.collect{
                a = it
            }

        }




         */
    }

}