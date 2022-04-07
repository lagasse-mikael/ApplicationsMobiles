package ca.qc.cstj.s08bottomnavigation.presentation.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    init {
        _count.value = 0
        //_delivery.value = Delivery()
    }

    fun add() {
        _count.value = _count.value!! + 1
    }

}