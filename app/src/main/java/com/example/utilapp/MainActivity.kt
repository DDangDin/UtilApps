package com.example.utilapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentManager
import com.example.utilapp.databinding.ActivityMainBinding
import com.example.utilapp.view.fragment.CalculatorFragment
import com.example.utilapp.view.fragment.CalendarFragment
import com.example.utilapp.view.fragment.TimerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

/**
implementation 'org.mariuszgromada.math:MathParser.org-mXparser:4.4.2'
 라이브러리 사용
 **/
class MainActivity : AppCompatActivity(){

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    lateinit var pageCalendar: CalendarFragment
    lateinit var pageCalculator: CalculatorFragment
    lateinit var pageTimer: TimerFragment

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pageCalendar = CalendarFragment()
        pageCalculator = CalculatorFragment()
        pageTimer = TimerFragment()

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.layout_frame, pageCalendar).commit()

        // navBar settings
        binding.navView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.item_calendar -> {
                    fragmentManager.beginTransaction().replace(R.id.layout_frame, pageCalendar).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.item_calculator -> {
                    fragmentManager.beginTransaction().replace(R.id.layout_frame, pageCalculator).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.item_timer -> {
                    fragmentManager.beginTransaction().replace(R.id.layout_frame, pageTimer).commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

    }

}