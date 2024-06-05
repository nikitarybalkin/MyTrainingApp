package com.example.myapplication2.presentation.utils

import android.content.Context
import com.example.myapplication2.R
import javax.inject.Inject

class TimeConverter(
    val context: Context
    ) {
    public fun convTime(time: String): String {
        val t = time.toInt()
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