package com.example.utilapp.util

import android.os.CountDownTimer

object CounterOptions {
    fun counterStart(countDownTimer: CountDownTimer) = countDownTimer.start()
    fun counterCancel(countDownTimer: CountDownTimer) = countDownTimer.cancel()
}