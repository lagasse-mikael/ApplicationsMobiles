package ca.qc.cstj.s08bottomnavigation.presentation.ui.settings

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.databinding.FragmentSetttingsBinding

class SettingsFragment : Fragment(R.layout.fragment_setttings) {

    private val binding: FragmentSetttingsBinding by viewBinding()
    private val viewModel: SettingsViewModel by viewModels()

    //Équivalent de la méthode onCreate dans une activité
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewLifecycleOwner équivalent de this dans une activité
        viewModel.count.observe(viewLifecycleOwner) {
            binding.txvCount.text = it.toString()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.add()
        }

    }

}