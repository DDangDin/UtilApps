package com.example.utilapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.utilapp.R
import com.example.utilapp.databinding.FragmentCalculatorBinding
import com.example.utilapp.util.Calculator.calculate
import com.example.utilapp.util.autoCleared
import com.example.utilapp.view.viewmodel.SharedViewModel
import com.google.android.material.button.MaterialButton

class CalculatorFragment : Fragment(R.layout.fragment_calculator), View.OnClickListener {

//    private var _binding: FragmentCalculatorBinding? = null
//    private val binding get() = _binding!!

    private var isOpenBracket = false
    private var binding by autoCleared<FragmentCalculatorBinding>() // use code snippet
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnC.setOnClickListener(this)
        binding.btnOpenBracket.setOnClickListener(this)
        binding.btnCloseBracket.setOnClickListener(this)
        binding.btnDivider.setOnClickListener(this)
        binding.btnMultiply.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnEquals.setOnClickListener(this)
        binding.btnAc.setOnClickListener(this)
        binding.btnDot.setOnClickListener(this)

        with(sharedViewModel) {
            calResultValue.observe(viewLifecycleOwner) {
                binding.tvResult.text = it
            }
            calSolutionValue.observe(viewLifecycleOwner) {
                binding.tvSolution.text = it
            }
        }

        return binding.root
    }

    override fun onClick(view: View?) {
        val btn: MaterialButton = view as MaterialButton
        val btnText = btn.text.toString()
        var dataToCalculate = binding.tvSolution.text.toString()
        val symbols = listOf("+", "-", "*", "/", "c")

        when (btnText) {
            "AC" -> {
                binding.tvSolution.text = ""
                binding.tvResult.text = "0"
                sharedViewModel.updateCalculatorValue("0", "")
                return
            }
            "c" -> {
                if (binding.tvSolution.text != "" &&
                    binding.tvResult.text != "0"
                ) {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length - 1)
                }
            }
            "=" -> {
                binding.tvSolution.text = binding.tvResult.text
                return
            }
            "(" -> {
                isOpenBracket = true
                dataToCalculate += btnText
            }
            ")" -> {
                isOpenBracket = false
                dataToCalculate += btnText
            }
            else -> {
                dataToCalculate += btnText
            }
        }
        binding.tvSolution.text = dataToCalculate
        Log.d("cal_log", dataToCalculate)
        if (btnText !in symbols) {
            if (!isOpenBracket) {
                val result = calculate(dataToCalculate)
                sharedViewModel.updateCalculatorValue(result, dataToCalculate)
            }
        }
    }

//    override fun onDestroyView() {    // solved
//        super.onDestroyView()
//        _binding = null
//    }
}