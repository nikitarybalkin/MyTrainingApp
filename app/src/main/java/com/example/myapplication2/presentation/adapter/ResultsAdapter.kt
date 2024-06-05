package com.example.myapplication2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.databinding.ListOfResultsBinding
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel

class ResultsAdapter(
    private val results: List<ResultsModel?>,
    private val goToDetailedResults: (Int) -> Unit,
    private val deleteResults: (ResultsModel) -> Unit

    ): RecyclerView.Adapter<ResultsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = ListOfResultsBinding.inflate(LayoutInflater.from(parent.context))
        return ResultsViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.binding.tvRNameOfTrain2.text = results[position]!!.nameOfTrain
        holder.binding.tvRDate2.text = results[position]!!.date
        holder.binding.tvRTime2.text = convTime(results[position]!!.time)
        holder.binding.RView.setOnClickListener{
            goToDetailedResults(position)
        }
        holder.binding.bDelete.setOnClickListener {
            deleteResults(results[position]!!)
        }
    }
}
private fun convTime(time: String): String {
    var t = time.toInt()
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

class ResultsViewHolder(val binding: ListOfResultsBinding) : ViewHolder(binding.root)