package com.example.utilapp.view.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utilapp.util.CounterInterface
import java.text.DecimalFormat
import java.text.NumberFormat

class TimerViewModel : ViewModel(), CounterInterface {
    private lateinit var timerObject: CountDownTimer

    private val _time = MutableLiveData("00:00:00")
    val time: LiveData<String>
        get() = _time

    private val _timeRunning = MutableLiveData(false)
    val timeRunning: LiveData<Boolean>
        get() = _timeRunning

    private val _validFinish = MutableLiveData<String>()
    val validFinish: LiveData<String> = _validFinish


    fun timerExecute(millisInFuture: Long, countDownInterval: Long) {
        if (millisInFuture.toInt() != 0) {
            _timeRunning.value = true

            timerObject = object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(p0: Long) {
                    val f: NumberFormat = DecimalFormat("00")
                    val hour: Long = p0 / 3600000 % 24
                    val min: Long = p0 / 60000 % 60
                    val sec: Long = p0 / 1000 % 60
                    _time.value = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
                }

                override fun onFinish() {
                    timerStop()
                    _validFinish.value = "타이머가 종료되었습니다."

                }
            }

            counterStart(timerObject)
        }

    }

    fun timerStop() {
        counterCancel(timerObject)
        _time.value = "00:00:00"
        _timeRunning.value = false
    }
}