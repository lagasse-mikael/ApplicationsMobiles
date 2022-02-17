package ca.mikeyy.tipscalculator.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.mikeyy.tipscalculator.domain.models.TipsCalculation

class MainViewModel : ViewModel() {

    private val _tipsCalculation = MutableLiveData<TipsCalculation>()

    val tipsCalculation : LiveData<TipsCalculation> get() = _tipsCalculation

    fun calculate(subotal:Double,tipPercentage:Int){
        _tipsCalculation.value = TipsCalculation(subotal,tipPercentage)
    }
}