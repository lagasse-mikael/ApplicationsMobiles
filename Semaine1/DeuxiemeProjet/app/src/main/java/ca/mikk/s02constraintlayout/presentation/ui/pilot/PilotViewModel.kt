package ca.mikk.s02constraintlayout.presentation.ui.pilot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.mikk.s02constraintlayout.core.notify
import ca.mikk.s02constraintlayout.domain.models.Pilot

class PilotViewModel : ViewModel(){

    private val _pilot = MutableLiveData<Pilot>()

    val pilot : LiveData<Pilot> get() = _pilot

    init {
        _pilot.value = Pilot("Bee Zoon",10,10)
    }

    // !!. = crash si null.
    // Sinon on prendrais ?.
    fun fly(revolution : Int, isTraining : Boolean) : Boolean{
        return if(_pilot.value!!.canFly()){
            _pilot.value!!.fly(revolution,isTraining)
            _pilot.notify()
            true
        } else {
            false
        }
    }

    fun recharge(){
        _pilot.value!!.recharge()
        _pilot.notify()
    }

}