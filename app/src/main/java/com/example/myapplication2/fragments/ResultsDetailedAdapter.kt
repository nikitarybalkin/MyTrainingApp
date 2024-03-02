package com.example.myapplication2.fragments

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.databinding.FragmentResultsDetailedBinding
import com.example.myapplication2.databinding.ListOfDetailedResultsBinding
import com.example.myapplication2.db.ResultsEntity

class ResultsDetailedAdapter(
    val listOfExes: ResultsEntity

): RecyclerView.Adapter<ResultsDetailedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsDetailedViewHolder {
        val binding = ListOfDetailedResultsBinding.inflate(LayoutInflater.from(parent.context))
        return ResultsDetailedViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfExes.exercises.size

    override fun onBindViewHolder(holder: ResultsDetailedViewHolder, position: Int) {
        Log.d(TAG, "position = $position")
        Log.d(TAG, "gettable ${listOfExes.countInSet.size}")
        holder.binding.tvRdNameOfExercise2.text = listOfExes.exercises[position]
        holder.binding.tvRdQuantityOfRepeats2.text = convWithoutBrackets(listOfExes.countInSet[position])
        holder.binding.tvRdQuantityOfSets2.text = listOfExes.countOfSets[position]
        holder.binding.tvRdWeight2.text = convWithoutBrackets(listOfExes.weights[position])
        holder.binding.tvRdTime2.text = convTime(listOfExes.time)
    }

    private fun convWithoutBrackets(t: String): String {
        return t.replace("[", "").replace("]", "")
    }

    private fun convTime(time: String): String {
        var t = time.toInt()
        if (t >= 60) {
            return "${t/60} мин. ${t%60} сек."
        } else return "$t сек."
    }

}

class ResultsDetailedViewHolder(val binding : ListOfDetailedResultsBinding): ViewHolder(binding.root)