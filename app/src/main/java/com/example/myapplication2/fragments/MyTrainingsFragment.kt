package com.example.myapplication2.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentCreateTrainBinding
import com.example.myapplication2.databinding.FragmentMyTrainings2Binding
import com.example.myapplication2.db.App
import com.example.myapplication2.db.TrainingEntity
import com.example.myapplication2.viewModels.CreateTrainViewModel
import com.example.myapplication2.viewModels.DetailedViewModel
import com.example.myapplication2.viewModels.MyTrainingsViewModel
import com.example.myapplication2.viewModels.ViewModelFactory
import javax.inject.Inject

class MyTrainingsFragment : Fragment() {

    /*companion object {
        fun newInstance() = MyTrainingsFragment()
    }

     */

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMyTrainings2Binding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MyTrainingsViewModel::class.java]
    }
    /*private val viewModel: MyTrainingsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val trainingDAO = (requireActivity().application as App).db.exerciseDao()
                return MyTrainingsViewModel(trainingDAO) as T
            }
        }
    }

     */


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyTrainings2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getAll()
        var a: String = ""

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel._list?.collect {
                it?.let {
                    //a = it[it.lastIndex].toString()
                    val myAdapter =
                        context?.let { it1 ->
                            MyAdapter(
                                it,
                                { num -> goToDetailed(num) },
                                { table -> delTable(table) },
                                context = it1
                            )
                        }
                    binding.recycler.adapter = myAdapter
                    if (it.isEmpty()) Toast.makeText(requireContext(), context?.getString(R.string.toast_of_trainings), Toast.LENGTH_SHORT).show()
                }


            }

        }


    }

    private fun goToDetailed(num: Int) {
        var bundle = Bundle()
        bundle.putInt("aue", num)
        //Log.d(TAG, "кладущееся число в бандл = $num")
        findNavController().navigate(R.id.action_myTrainingsFragment_to_detailedFragment, bundle)

    }

    private fun delTable(table: TrainingEntity) {
        viewModel.delete(table)
    }


}