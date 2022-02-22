package ca.mikeyy.tipscalculator.core

import java.text.NumberFormat

object Formatter {

    fun toMoneyFormat(amount:Double) : String {
        val numberFormat = NumberFormat.getCurrencyInstance().format(amount)
        return numberFormat
    }
}