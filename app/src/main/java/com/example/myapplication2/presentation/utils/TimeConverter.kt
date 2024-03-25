package com.example.myapplication2.presentation.utils

import android.content.Context
import com.example.myapplication2.R
import javax.inject.Inject

class TimeConverter @Inject constructor(
    val context: Context
    ) {
    public fun convTime(time: String): String {
        var t = time.toInt()
        var sec = context?.getString(R.string.sec)
        var min = context?.getString(R.string.min)
        var hour = context?.getString(R.string.hour)
        var res: String = ""
        when (t) {
            in 1..59 -> res = "$t $sec"
            in 60..3599 -> {
                if (t % 60 == 0) res = "${t / 60} $min"
                else res = "${t / 60} $min ${t % 60} $sec"
            }

            in 3600..Int.MAX_VALUE -> {
                if (t % 3600 == 0) {
                    res = "${t / 3600} $hour"
                } else if (t % 3600 < 60) {
                    res = "${t / 3600} $hour ${t % 3600} $sec"
                } else if (t % 3600 > 59) {
                    if ((t % 3600) % 60 == 0) {
                        res = "${t / 3600} $hour ${(t % 3600) / 60} $min"
                    }
                }
                if ((t % 3600) % 60 != 0) {
                    res = "${t / 3600} $hour ${(t % 3600) / 60} $min ${(t % 3600) % 60} $sec"
                }
            }

            else -> res = "123"
        }
        return res

    }
}