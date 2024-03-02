package com.example.myapplication2.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentTrainBinding
import com.example.myapplication2.db.App
import com.example.myapplication2.db.ResultsEntity
import com.example.myapplication2.db.TrainingEntity
import com.example.myapplication2.viewModels.TrainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class TrainFragment : Fragment() {

    companion object {
        fun newInstance() = TrainFragment()
    }

    private lateinit var binding: FragmentTrainBinding
    private val viewModel: TrainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val resultsDAO = (requireActivity().application as App).db.resultsDao()
                val trainingDAO = (requireActivity().application as App).db.exerciseDao()
                return TrainViewModel(resultsDAO, trainingDAO) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.timer()
        viewModel.getAll()
        var resList1: List<TrainingEntity> = emptyList()
        var bundle = arguments?.getInt("NameOfList")
        viewModel.getAll()
        var counter: Int = 0
        var quantityOfSets = 0
        var listOfQuantityOfSets: MutableList<String> = emptyList<String>().toMutableList()
        var listOfQuantityOfExes: MutableList<String> = emptyList<String>().toMutableList()
        var listOfQuantityInOne: MutableList<String> = emptyList<String>().toMutableList()
        var listOfWeight: MutableList<String> = emptyList<String>().toMutableList()
        var listOfWeightInOne: MutableList<String> = emptyList<String>().toMutableList()
        //var date: DateFormat = SimpleDateFormat("dd.MM.yyyy", Calendar.getInstance().time)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.resList.collect {
                binding.tvNameOfExercise.text = it[bundle!!].exercises!![counter]
                binding.tvNameOfTrain.text = it[bundle].nameOfTrainingEntity
                resList1 = it
                Log.d(TAG, "reslist1 = $resList1")


            }

        }

        binding.bNSet.setOnClickListener {
            if (binding.etQuantityOfRepeats.text.isNotEmpty() && binding.etWeight.text.isNotEmpty()) {

                quantityOfSets++
                listOfQuantityInOne.add(
                    binding.etQuantityOfRepeats.text.toString()
                )
                Log.d(TAG, "listOfQuantityInOne = $listOfQuantityInOne")
                Log.d(TAG, "toStr = ${binding.etQuantityOfRepeats.text.toString()} и replace = ${binding.etQuantityOfRepeats.text.toString().replace(",", " |")}")
                listOfWeightInOne.add(binding.etWeight.text.toString().replace(",", " |"))
                Log.d(TAG, "listOfWeightInOne = $listOfWeightInOne")
                binding.tvNumbOfSet.text = "It's your ${quantityOfSets + 1} set"
                binding.etQuantityOfRepeats.text.clear()
                binding.etWeight.text.clear()

            } else Toast.makeText(context, context?.getString(R.string.some_fields_empty), Toast.LENGTH_SHORT).show()
        }


        binding.bNExercise.setOnClickListener {

            if (counter >= resList1[bundle!!].exercises!!.lastIndex) {
                listOfQuantityOfExes.add(listOfQuantityInOne.toString().replace(",", " |"))
                listOfWeight.add(listOfWeightInOne.toString().replace(",", " |"))
                listOfQuantityOfSets.add(quantityOfSets.toString())
                viewModel.insert(
                    ResultsEntity(
                        exercises = resList1[bundle].exercises!!,
                        countOfSets = listOfQuantityOfSets,
                        countInSet = listOfQuantityOfExes,
                        weights = listOfWeight,
                        date = getDate(),
                        nameOfTrain = resList1[bundle].nameOfTrainingEntity,
                        time = viewModel.time.toString()
                    )
                )
                Log.d(
                    TAG,
                    "ResultsEntity = exercises = ${resList1[bundle].exercises!!}, countOfSets = $quantityOfSets, countInSet = $listOfQuantityOfExes, weights = $listOfWeight"
                )
                Toast.makeText(context, context?.getString(R.string.training_is_over), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_trainFragment_to_startFragment)
            } else {

                listOfQuantityOfSets.add(quantityOfSets.toString())
                quantityOfSets = 0
                counter++
                binding.tvNameOfExercise.text = resList1[bundle].exercises!![counter]
                binding.tvNameOfTrain.text = resList1[bundle].nameOfTrainingEntity
                listOfQuantityOfExes.add(listOfQuantityInOne.toString().replace(",", " |"))
                listOfWeight.add(listOfWeightInOne.toString().replace(",", " |"))
                //Log.d(TAG, "final l_Q = $listOfQuantityOfExes ||| final l_W = $listOfWeight")
                binding.tvNumbOfSet.text = context?.getString(R.string.its_first_set)
                listOfQuantityInOne.clear()
                listOfWeightInOne.clear()

            }

        }

    }

    private fun getDate(): String {
        val calendar = Calendar.getInstance()
        return StringBuilder().append(calendar[Calendar.DAY_OF_MONTH]).append("/").
        append(calendar[Calendar.MONTH] + 1)
            .append("/").append(calendar[Calendar.YEAR]).append(" ").toString()
    }


}