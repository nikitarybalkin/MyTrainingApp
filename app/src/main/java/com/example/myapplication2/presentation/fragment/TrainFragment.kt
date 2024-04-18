package com.example.myapplication2.presentation.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentTrainBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.presentation.viewModel.TrainViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import kotlinx.coroutines.flow.collect
import java.util.Calendar
import javax.inject.Inject

class TrainFragment : Fragment() {

    companion object {
        fun newInstance() = TrainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TrainViewModel::class.java]
    }
    private lateinit var binding: FragmentTrainBinding
    var counter: Int = 0
    var quantityOfSets = 0
    var indexOfExercise1: Int? = null
    var listOfQuantityOfSets: MutableList<String> = emptyList<String>().toMutableList()
    var listOfQuantityOfExes: MutableList<String> = emptyList<String>().toMutableList()
    var listOfQuantityInOne: MutableList<String> = emptyList<String>().toMutableList()
    var listOfWeight: MutableList<String> = emptyList<String>().toMutableList()
    var listOfExercises: MutableList<String> = emptyList<String>().toMutableList()
    var listOfWeightInOne: MutableList<String> = emptyList<String>().toMutableList()
    var listOfIndexes: MutableList<Int> = emptyList<Int>().toMutableList()
    var bundle: Int? = null
    var resList1: List<TrainingModel> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startTimer()
        viewModel.getAll()
        viewModel.getAll()
        bundle = arguments?.getInt("NameOfList")
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.resList.collect {
                binding.tvNameOfExercise.text = "Выберете упражнение"
                //binding.tvNameOfExercise.text = it[bundle!!].exercises!![counter]
                binding.tvNameOfTrain.text = it[bundle!!].nameOfTrainingEntity
                resList1 = it
                Log.d(TAG, "reslist1 = $resList1")
            }
        }

        binding.tvNameOfExercise.setOnClickListener {
            var bundleToSheet = Bundle()
            bundleToSheet.putStringArray(
                "listOfExercises",
                resList1[bundle!!].exercises?.toTypedArray()
            )
            Log.d("LOL", "бандл готов = ${resList1[bundle!!].exercises?.toTypedArray()}")
            findNavController().navigate(
                R.id.action_trainFragment_to_trainBottomSheetFragment,
                bundleToSheet
            )
            //TrainBottomSheetFragment().show(childFragmentManager, "trainBottomSheet")
        }
        Log.d("LOL", "vm in train ${viewModel}")
        viewModel.indexOfExercise.observe(viewLifecycleOwner) {
            Log.d("LOL", "заколлектило")
            if (it != null) {
                indexOfExercise1 = it
                Log.d("LOL", "setIndex works")
                setExercise()
            }
        }

        binding.bNExercise.setOnClickListener {
            if (counter >= resList1[bundle!!].exercises!!.lastIndex) {
                listOfQuantityOfExes.add(listOfQuantityInOne.toString().replace(",", " |"))
                listOfWeight.add(listOfWeightInOne.toString().replace(",", " |"))
                listOfQuantityOfSets.add(quantityOfSets.toString())
                viewModel.stopTimer()
                viewModel.insert(
                    ResultsModel(
                        exercises = listOfExercises,
                        countOfSets = listOfQuantityOfSets,
                        countInSet = listOfQuantityOfExes,
                        weights = listOfWeight,
                        date = getDate(),
                        nameOfTrain = resList1[bundle!!].nameOfTrainingEntity,
                        time = viewModel.time.toString()
                    )
                )
                Log.d(
                    TAG,
                    "ResultsEntity = exercises = ${listOfExercises}, countOfSets = $quantityOfSets, countInSet = $listOfQuantityOfExes, weights = $listOfWeight"
                )
                Toast.makeText(
                    context,
                    context?.getString(R.string.training_is_over),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_trainFragment_to_startFragment)
            } else {
                listOfIndexes.add(indexOfExercise1!!)
                listOfExercises.add(resList1[bundle!!].exercises!![counter])
                listOfQuantityOfSets.add(
                    element = quantityOfSets.toString()
                )
                quantityOfSets = 0
                counter++
                binding.tvNameOfExercise.text = resList1[bundle!!].exercises!![counter]
                binding.tvNameOfTrain.text = resList1[bundle!!].nameOfTrainingEntity
                listOfQuantityOfExes.add(
                    element = listOfQuantityInOne.toString().replace(",", " |")
                )
                listOfWeight.add(
                    element = listOfWeightInOne.toString().replace(",", " |")
                )
                binding.tvNumbOfSet.text = context?.getString(R.string.its_first_set)
                listOfQuantityInOne.clear()
                listOfWeightInOne.clear()
            }
        }

    }

    fun setExercise() {
        binding.tvNameOfExercise.text = resList1[bundle!!].exercises!![indexOfExercise1!!]
        binding.bNSet.setOnClickListener {
            if (binding.etQuantityOfRepeats.text.isNotEmpty() && binding.etWeight.text.isNotEmpty()) {
                quantityOfSets++
                listOfQuantityInOne.add(element = binding.etQuantityOfRepeats.text.toString())
                Log.d(TAG, "listOfQuantityInOne = $listOfQuantityInOne")
                Log.d(
                    TAG,
                    "toStr = ${binding.etQuantityOfRepeats.text.toString()} и replace = ${
                        binding.etQuantityOfRepeats.text.toString().replace(",", " |")
                    }"
                )
                listOfWeightInOne.add(binding.etWeight.text.toString().replace(",", " |"))
                Log.d(TAG, "listOfWeightInOne = $listOfWeightInOne")
                binding.tvNumbOfSet.text = "It's your ${quantityOfSets + 1} set"
                binding.etQuantityOfRepeats.text.clear()
                binding.etWeight.text.clear()
            } else Toast.makeText(
                context,
                context?.getString(R.string.some_fields_empty),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun getDate(): String {
        val calendar = Calendar.getInstance()
        return StringBuilder().append(calendar[Calendar.DAY_OF_MONTH]).append("/")
            .append(calendar[Calendar.MONTH] + 1)
            .append("/").append(calendar[Calendar.YEAR]).append(" ").toString()
    }
}