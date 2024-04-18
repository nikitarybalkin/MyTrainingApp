package com.example.myapplication2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication2.databinding.ListOfTrainBottomSheetBinding

class TrainBottomSheetAdapter(
    var listOfExercise: List<String>?,
    val changeExercise: (Int) -> Unit
    ): Adapter<TrainBottomSheetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainBottomSheetViewHolder {
        val binding = ListOfTrainBottomSheetBinding.inflate(LayoutInflater.from(parent.context))
        return TrainBottomSheetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (listOfExercise == null) {
            return 0
        } else {
            return listOfExercise!!.size
        }
    }

    override fun onBindViewHolder(holder: TrainBottomSheetViewHolder, position: Int) {
        holder.binding.let {
            it.tvNameOfExercise.text = listOfExercise?.get(position)
            it.tvNameOfExercise.setOnClickListener {
                changeExercise(position)
            }

        }
    }
}

class TrainBottomSheetViewHolder(val binding: ListOfTrainBottomSheetBinding): RecyclerView.ViewHolder(binding.root)