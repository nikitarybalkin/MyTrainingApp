package com.example.myapplication2.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentCreateTrainBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.presentation.viewModel.CreateTrainViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class CreateTrainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentCreateTrainBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CreateTrainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTrainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val lExes = mutableListOf<String>()
        var counter = 0
        var nameOfTrain: String? = context?.getString(R.string.no_name)
        binding.b1.text = context?.getString(R.string.next_exercise)
        binding.b2.text = context?.getString(R.string.save_training)

        binding.b1.setOnClickListener() {

            if (binding.edNameOfTrain.text.isNotEmpty() && binding.edNameOfExercise.text.isNotEmpty()) {
                nameOfTrain = binding.edNameOfTrain.text.toString()
                lExes?.add(binding.edNameOfExercise.text.toString())
                binding.edNameOfExercise.text.clear()
                counter++
                binding.tvRememb.text = "${requireActivity().applicationContext.getText(R.string.you_wrote)} $counter ${requireActivity().applicationContext.getText(R.string.exercises)}"
            }

        }

        binding.b2.setOnClickListener() {
            viewModel.insert(TrainingModel(nameOfTrain!!, lExes))
            findNavController().navigate(R.id.action_createTrainFragment_to_homeFragment)

        }
    }

}