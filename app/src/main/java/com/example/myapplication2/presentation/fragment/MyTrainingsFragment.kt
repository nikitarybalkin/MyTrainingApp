package com.example.myapplication2.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.databinding.FragmentMyTrainings2Binding
import com.example.myapplication2.di.App
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.presentation.adapter.MyAdapter
import com.example.myapplication2.presentation.viewModel.MyTrainingsViewModel
import com.example.myapplication2.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class MyTrainingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMyTrainings2Binding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MyTrainingsViewModel::class.java]
    }

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
        binding.myTrainingsRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getAll()
        var a: String = ""

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel._list?.collect {
                it?.let {
                    val myAdapter =
                        context?.let { it1 ->
                            MyAdapter(
                                it,
                                { num -> goToDetailed(num) },
                                { table -> delTable(table) },
                                context = it1
                            )
                        }
                    binding.myTrainingsRecycler.adapter = myAdapter
                    if (it.isEmpty()) Toast.makeText(
                        requireContext(),
                        context?.getString(R.string.toast_of_trainings),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun goToDetailed(num: Int) {
        val bundle = Bundle()
        bundle.putInt("aue", num)
        findNavController().navigate(R.id.action_myTrainingsFragment_to_detailedFragment, bundle)
    }

    private fun delTable(table: TrainingModel) {
        viewModel.delete(table)
    }
}