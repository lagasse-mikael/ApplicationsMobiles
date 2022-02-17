package ca.mikeyy.tipscalculator.domain.models

import ca.mikeyy.tipscalculator.core.Constants

data class TipsCalculation(private val subtotal : Double,private val percentageTip : Int) {

    val tipsAmount = subtotal * (percentageTip / Constants.PERCENT)
    val TPS = subtotal * Constants.pTPS
    val TVQ = subtotal * Constants.pTVQ
    val total = subtotal + tipsAmount + TPS + TVQ

}