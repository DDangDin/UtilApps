package com.example.utilapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.utilapp.R
import com.example.utilapp.databinding.FragmentCalendarBinding
import com.example.utilapp.util.autoCleared
import com.example.utilapp.view.viewmodel.SharedViewModel
import java.util.*

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private var binding by autoCleared<FragmentCalendarBinding>() // use code snippet
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        moveToCurrentDate()

        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            binding.tvDate.text = "<선택 날짜>\n\n${year}년 ${month+1}월 ${day}일"
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        moveToCurrentDate()
    }

    fun moveToCurrentDate(){
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        binding.calendarView.setDate(cal.timeInMillis, true, true)
        binding.tvDate.text = "<현재 날짜>\n\n${year}년 ${month+1}월 ${day}일"
    }
}