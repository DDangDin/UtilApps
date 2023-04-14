package com.example.utilapp.util

import android.widget.Toast
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

object Calculator {

    fun calculate(data: String): String {
        val result = try {
            val df = DecimalFormat("#.##")
            val expression = data
            var temp = df.format(Expression(expression).calculate()).toString()
            if (temp.endsWith(".0")){
                temp = temp.replace(".0", "")
            }
            temp
        } catch (e: java.lang.Exception) {
            "error"
        }
        return result
    }
}