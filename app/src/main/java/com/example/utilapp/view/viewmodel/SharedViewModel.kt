package com.example.utilapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var _calResultValue: MutableLiveData<String> = MutableLiveData("0")
    val calResultValue: LiveData<String> = _calResultValue

    private var _calSolutionValue: MutableLiveData<String> = MutableLiveData("")
    val calSolutionValue: LiveData<String> = _calSolutionValue

    fun updateCalculatorValue(newResultValue: String, newSolutionValue: String) {
        _calResultValue.value = newResultValue
        _calSolutionValue.value = newSolutionValue
    }
}