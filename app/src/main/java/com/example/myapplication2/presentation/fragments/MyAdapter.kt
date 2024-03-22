package com.example.myapplication2.presentation.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.R
import com.example.myapplication2.databinding.ListItemBinding
import com.example.myapplication2.data.TrainingEntity


class MyAdapter(
    private val table: List<TrainingEntity>,
    var navigationAction: (Int) -> Unit,
    var delTable: (TrainingEntity) -> Unit,
    var context: Context
) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = table.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvNameOfTrainField.text =
            "${context?.getString(R.string.name_of_train)}:  ${table[position].nameOfTrainingEntity}"
        holder.binding.tvCountOfExercisesField.text =
            "${context?.getString(R.string.quantity_of_exercises)}: ${table[position].exercises?.size.toString()}"
        holder.binding.tvNameOfTrainField.setOnClickListener {
            navigationAction(position)
        }
        holder.binding.bDelete.setOnClickListener {
            delTable(table[position])
        }

    }


}

class MyViewHolder(val binding: ListItemBinding) : ViewHolder(binding.root)