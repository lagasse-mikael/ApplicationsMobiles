package ca.mikk.s02constraintlayout.domain.models

import kotlin.random.Random

data class Pilot(var name:String,var life: Int,var cube:Int = 0) {

    val DEFAULT_LIFE = life;
    val DEFAULT_ENERGY = 7;
    val DEFAULT_SHIELD = 5;

    private var _experince: Int = 0
    var shield: Int = 5
    var energy: Int = 7

    val level:Int get() = _experince / 10

    fun fly(revolutions:Int, isTraining:Boolean){
        if(!isTraining){
            _experince += revolutions * Random.nextInt(1,6)
            life -= Random.nextInt(0,2)
            shield -= Random.nextInt(0,6)
            energy--
            cube += Random.nextInt(0,revolutions + 1)
        }
    }

    fun canFly():Boolean{
        return life > 0 && energy > 0 && shield > 0
    }

    // TO DO , constantes
    fun recharge(){
        energy = DEFAULT_ENERGY
        shield = DEFAULT_SHIELD
        life = DEFAULT_LIFE

    }
}