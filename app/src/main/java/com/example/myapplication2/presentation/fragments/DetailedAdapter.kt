package com.example.myapplication2.presentation.fragments

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.databinding.ExerciseslistBinding

class DetailedAdapter(private val listOfExes: List<String>): Adapter<DetailedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedViewHolder {
        val binding = ExerciseslistBinding.inflate(LayoutInflater.from(parent.context))
        return DetailedViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfExes.size

    override fun onBindViewHolder(holder: DetailedViewHolder, position: Int) {
        holder.binding.tvOneExercise.text = "${position + 1}. ${listOfExes[position]}"
    }

}

class DetailedViewHolder(val binding: ExerciseslistBinding) : ViewHolder(binding.root)