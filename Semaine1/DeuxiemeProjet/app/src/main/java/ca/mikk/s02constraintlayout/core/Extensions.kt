package ca.mikk.s02constraintlayout.core

import androidx.lifecycle.MutableLiveData

fun String.laMethodeMikael() : String{
    return "OK"
}

fun <T> MutableLiveData<T>.notify(){
    this.postValue(this)
}