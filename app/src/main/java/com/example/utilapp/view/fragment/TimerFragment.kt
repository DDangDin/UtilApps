package com.example.utilapp.view.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.utilapp.MainActivity
import com.example.utilapp.R
import com.example.utilapp.databinding.FragmentTimerBinding
import com.example.utilapp.util.autoCleared
import com.example.utilapp.view.viewmodel.SharedViewModel
import com.example.utilapp.view.viewmodel.TimerViewModel

class TimerFragment : Fragment(R.layout.fragment_timer) {

    private var binding by autoCleared<FragmentTimerBinding>() // use code snippet
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var mainContext: MainActivity
    private val viewModel: TimerViewModel by activityViewModels()
    private var time_sec: Long = 0
    private var time_min: Long = 0
    private var time_hour: Long = 0
    private var timeRunnig = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context as MainActivity
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = FragmentTimerBinding.inflate(inflater, container, false)

        numberPickerSetting(binding.npSec, false)
        numberPickerSetting(binding.npMin, false)
        numberPickerSetting(binding.npHour, true)

        binding.npSec.setOnValueChangedListener { numberPicker, oldValue, newValue ->
            time_sec = (newValue * 1000 + 1000).toLong()    // 1000 -> 1초
        }

        binding.npMin.setOnValueChangedListener { numberPicker, oldValue, newValue ->
            time_min = (newValue * 60000).toLong()
        }

        binding.npHour.setOnValueChangedListener { numberPicker, oldValue, newValue ->
            time_hour = (newValue * 3600000).toLong()
        }

        viewModel.timeRunning.observe(mainContext) { running ->
            timeRunnig = running
        }
        binding.btnStart.setOnClickListener {
            if (!timeRunnig) {
                Log.d("timer_viewmodel", time_sec.toString())
                val timeTotal = time_sec + time_min + time_hour
                viewModel.timerExecute(timeTotal.toLong(), 1000)
            }
        }

        viewModel.time.observe(mainContext) {
            binding.tvTime.text = it.toString()
        }

        binding.btnStop.setOnClickListener {
            viewModel.timerStop()
        }

        viewModel.validFinish.observe(mainContext) {finish ->
            Toast.makeText(mainContext, finish, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun numberPickerSetting(np: NumberPicker, isHour: Boolean) {
        np.textColor = resources.getColor(R.color.white)
        np.minValue = 0
        np.maxValue = if (isHour) 24 else 60
        np.wrapSelectorWheel = true

    }
}