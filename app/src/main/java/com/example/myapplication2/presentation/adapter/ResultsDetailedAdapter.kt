package com.example.myapplication2.presentation.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.databinding.ListOfDetailedResultsBinding
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel

class ResultsDetailedAdapter(
    val listOfExes: ResultsModel

) : RecyclerView.Adapter<ResultsDetailedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsDetailedViewHolder {
        val binding = ListOfDetailedResultsBinding.inflate(LayoutInflater.from(parent.context))
        return ResultsDetailedViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfExes.exercises.size

    override fun onBindViewHolder(holder: ResultsDetailedViewHolder, position: Int) {
        Log.d(TAG, "position = $position")
        Log.d(TAG, "gettable ${listOfExes.countInSet.size}")
        holder.binding.tvRdNameOfExercise2.text = listOfExes.exercises[position]
        holder.binding.tvRdQuantityOfRepeats2.text =
            convWithoutBrackets(listOfExes.countInSet[position])
        holder.binding.tvRdQuantityOfSets2.text = listOfExes.countOfSets[position]
        holder.binding.tvRdWeight2.text = convWithoutBrackets(listOfExes.weights[position])
        //holder.binding.tvRdTime2.text = convTime(listOfExes.time)
        if (position == 0) {
            holder.binding.tvRdTime.visibility = View.VISIBLE
            holder.binding.tvRdTime2.visibility = View.VISIBLE
            holder.binding.tvRdTime2.text = convTime(listOfExes.time)
        }
    }

    private fun convWithoutBrackets(t: String): String {
        return t.replace("[", "").replace("]", "")
    }

    private fun convTime(time: String): String {
        var t = time.toInt()
        /*if (t >= 60) {
            return "${t/60} мин. ${t%60} сек."
        } else return "$t сек."

         */


        var res: String = ""
        when (t) {
            in 1..59 -> res = "$t сек."
            in 60..3599 -> {
                if (t % 60 == 0) res = "${t / 60} мин."
                else res = "${t / 60} мин. ${t % 60} сек."
            }

            in 3600..Int.MAX_VALUE -> {
                if (t % 3600 == 0) {
                    res = "${t / 3600} час."
                } else if (t % 3600 < 60) {
                    res = "${t / 3600} час. ${t % 3600} сек."
                } else if (t % 3600 > 59) {
                    if ((t % 3600) % 60 == 0) {
                        res = "${t / 3600} час. ${(t % 3600) / 60} мин."
                    }
                }
                if ((t % 3600) % 60 != 0) {
                    res = "${t / 3600} час. ${(t % 3600) / 60} мин. ${(t % 3600) % 60} сек."
                }
            }

            else -> res = "123"
        }
        return res

    }
}


class ResultsDetailedViewHolder(val binding: ListOfDetailedResultsBinding) :
    ViewHolder(binding.root)