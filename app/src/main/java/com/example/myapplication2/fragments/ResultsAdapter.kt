package com.example.myapplication2.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.databinding.ExerciseslistBinding
import com.example.myapplication2.databinding.ListOfResultsBinding
import com.example.myapplication2.db.ResultsEntity

class ResultsAdapter(
    private val results: List<ResultsEntity>,
    private val goToDetailedResults: (Int) -> Unit,

): RecyclerView.Adapter<ResultsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = ListOfResultsBinding.inflate(LayoutInflater.from(parent.context))
        return ResultsViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.binding.tvRNameOfTrain2.text = results[position].nameOfTrain
        holder.binding.tvRDate2.text = results[position].date
        holder.binding.tvRQuantityOfExes2.text = results[position].exercises.size.toString()
        holder.binding.RView.setOnClickListener{
            goToDetailedResults(position)
        }

    }
}

class ResultsViewHolder(val binding: ListOfResultsBinding) : ViewHolder(binding.root)