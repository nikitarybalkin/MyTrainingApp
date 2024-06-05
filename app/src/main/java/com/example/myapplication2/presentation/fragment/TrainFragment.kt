package com.example.myapplication2.presentation.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentTrainBinding
import com.example.myapplication2.di.App
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.presentation.adapter.TrainBottomSheetAdapter
import com.example.myapplication2.presentation.viewModel.TrainViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
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
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var binding: FragmentTrainBinding
    var counter: Int = 0
    var quantityOfSets = 0
    var indexOfExercise1: Int? = null
    var listOfQuantityOfSets: MutableList<String> = mutableListOf()
    var listOfQuantityOfExes: MutableList<String> = mutableListOf()
    var listOfQuantityInOne: MutableList<String> = mutableListOf()
    var listOfWeight: MutableList<String> = mutableListOf()
    var listOfExercises: MutableList<String> = mutableListOf()
    var listOfWeightInOne: MutableList<String> = mutableListOf()
    var listOfIndexes: MutableList<Int> = mutableListOf()
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
        viewModel.getAll()
        val bottomSheetFragment: ConstraintLayout = binding.persistentBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetFragment)
        if (arguments != null) {
            viewModel.startTimer()
            bundle = arguments?.getInt("NameOfList")
            viewModel.numbOfList = bundle
            Log.d("LOL", "arguments != null")
            Log.d("LOL", "arguments = ${arguments}")
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.resList.collect {
                    Log.d("LOL", "reslist произошёл")
                    binding.tvNameOfExercise.text = getString(R.string.choose_excercise)
                    binding.tvNameOfTrain.text = it[bundle!!].nameOfTrainingEntity
                    resList1 = it
                }
            }
        } else {
            bundle = viewModel.numbOfList
            Log.d("LOL", "arguments = null")
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.resList.collect {
                    binding.tvNameOfExercise.text = getString(R.string.choose_excercise)
                    binding.tvNameOfTrain.text = it[bundle!!].nameOfTrainingEntity
                    resList1 = it
                }
            }
        }

        binding.tvNameOfExercise.setOnClickListener {
            if (quantityOfSets != 0) {
                nextExercise()
            }
            var bundleToSheet = Bundle()
            //viewModel.resListValue = resList1[bundle!!].exercises!!
            val adapter = TrainBottomSheetAdapter(
                viewModel.resListValue[bundle!!].exercises,
                { index1 -> setIndex(index1) }
            )
            binding.rvTrainBottomSheet.adapter = adapter
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        viewModel.indexOfExercise.observe(viewLifecycleOwner) {
            if (it != null) {
                indexOfExercise1 = it
                setExercise()
                binding.tvQuantityOfRepeats.visibility = View.VISIBLE
                binding.tvWeight.visibility = View.VISIBLE
                binding.etQuantityOfRepeats.visibility = View.VISIBLE
                binding.etWeight.visibility = View.VISIBLE
                binding.bFinish.visibility = View.VISIBLE
                binding.bNSet.visibility = View.VISIBLE
            }
        }

        binding.bFinish.setOnClickListener {
                finishTraining()
        }
    }

    private fun setIndex(index: Int) {
        viewModel.indexOfExercise.value = index
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setExercise() {
        listOfExercises
            .add(
                viewModel.resListValue[viewModel.numbOfList!!]
                    .exercises!![
                    indexOfExercise1!!]
            )
        binding.tvNameOfExercise.text = viewModel.resListValue[bundle!!].exercises!![indexOfExercise1!!]
        binding.bNSet.setOnClickListener {
            if (binding.etQuantityOfRepeats.text.isNotEmpty() && binding.etWeight.text.isNotEmpty()) {
                quantityOfSets++
                listOfQuantityInOne.add(binding.etQuantityOfRepeats.text.toString())
                listOfWeightInOne.add(binding.etWeight.text.toString().replace(",", " |"))
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

    private fun nextExercise() {
        listOfQuantityOfSets.add(quantityOfSets.toString())
        quantityOfSets = 0
        counter++
        viewModel.counter.value = counter
        binding.tvNameOfExercise.text = resList1[bundle!!].exercises!![counter]
        binding.tvNameOfTrain.text = resList1[bundle!!].nameOfTrainingEntity
        listOfQuantityOfExes.add(
            listOfQuantityInOne.toString().replace(",", " |").replace("[", "").replace("]", "")
        )
        listOfWeight.add(
            listOfWeightInOne.toString().replace(",", " |").replace(",", " |").replace("[", "")
                .replace("]", "")
        )
        binding.tvNumbOfSet.text = context?.getString(R.string.its_first_set)
        listOfQuantityInOne.clear()
        listOfWeightInOne.clear()
    }

    private fun finishTraining() {
        listOfQuantityOfExes.add(
            listOfQuantityInOne.toString().replace(",", " |").replace(",", " |").replace("[", "")
                .replace("]", "")
        )
        listOfWeight.add(
            listOfWeightInOne.toString().replace(",", " |").replace(",", " |").replace("[", "")
                .replace("]", "")
        )
        listOfQuantityOfSets.add(quantityOfSets.toString())
        viewModel.stopTimer()
        var resModel = ResultsModel(
            exercises = listOfExercises.toList(),
            countOfSets = listOfQuantityOfSets.toList(),
            countInSet = listOfQuantityOfExes.toList(),
            weights = listOfWeight.toList(),
            date = getDate(),
            nameOfTrain = resList1[bundle!!].nameOfTrainingEntity,
            time = viewModel.time.toString()
        )
        viewModel.insert(
            resModel
        )
        Toast.makeText(
            context,
            context?.getString(R.string.training_is_over),
            Toast.LENGTH_SHORT
        ).show()
        viewModel.indexOfExercise = MutableLiveData(null)
        findNavController().navigate(R.id.action_trainFragment_to_homeFragment)
    }


    private fun getDate(): String {
        val calendar = Calendar.getInstance()
        return StringBuilder().append(calendar[Calendar.DAY_OF_MONTH]).append("/")
            .append(calendar[Calendar.MONTH] + 1)
            .append("/").append(calendar[Calendar.YEAR]).append(" ").toString()
    }
}