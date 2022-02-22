package ca.mikeyy.tipscalculator.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ca.mikeyy.tipscalculator.R
import ca.mikeyy.tipscalculator.core.Formatter
import ca.mikeyy.tipscalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            try{
                val subtotal = binding.tilSubTotal.editText!!.text.toString().toDouble()
                val tips = binding.tilTips.editText!!.text.toString().toInt()
                viewModel.calculate(subtotal,tips)
            }catch (ex:NumberFormatException){
                Toast.makeText(this,"Erreur",Toast.LENGTH_LONG).show()
            }
        }

        viewModel.tipsCalculation.observe(this) {
            with(binding){
                lblTips.text = Formatter.toMoneyFormat(viewModel.tipsCalculation.value!!.tipsAmount)
                lblTPS.text = Formatter.toMoneyFormat(viewModel.tipsCalculation.value!!.TPS)
                lblTVQ.text = Formatter.toMoneyFormat(viewModel.tipsCalculation.value!!.TVQ)
                lblTotal.text = Formatter.toMoneyFormat(viewModel.tipsCalculation.value!!.total)
            }
        }

    }
}