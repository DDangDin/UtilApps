package com.example.utilapp.util

import android.os.CountDownTimer

interface CounterInterface {
    fun counterStart(countDownTimer: CountDownTimer) = countDownTimer.start()
    fun counterCancel(countDownTimer: CountDownTimer) = countDownTimer.cancel()

}