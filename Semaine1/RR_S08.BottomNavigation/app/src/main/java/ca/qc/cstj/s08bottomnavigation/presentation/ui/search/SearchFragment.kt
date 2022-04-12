package ca.qc.cstj.s08bottomnavigation.presentation.ui.search

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.core.Constants
import ca.qc.cstj.s08bottomnavigation.core.LoadingResource
import ca.qc.cstj.s08bottomnavigation.databinding.FragmentSearchBinding
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()

    private lateinit var ctlMainActivity : ConstraintLayout

    //Équivalent de la méthode onCreate dans une activité
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)

        viewModel.meteo.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResource.Error -> {
                    binding.pgbLoading.hide()
                    Log.e("ERROR RETRIEVE", it.throwable.message!!)
                    val snackbar =
                        Snackbar.make(binding.root, it.throwable.message!!, Snackbar.LENGTH_LONG)
                            .show()
                    binding.txvNotAvailable.visibility = View.VISIBLE
                }
                is LoadingResource.Loading -> {
                    binding.pgbLoading.show()
                    binding.grpMeteo.visibility = View.INVISIBLE
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                }
                is LoadingResource.Success -> {
                    binding.pgbLoading.hide()
                    binding.grpMeteo.visibility = View.VISIBLE
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                    showMeteo(it.data!!)
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            val cityName = binding.tilSearch.editText!!.text.toString()
            viewModel.search(cityName)
        }

    }

    private fun showMeteo(meteo: Meteo) {
        // Affichage de chacune des information de la meteo

        with(binding) {
            txvCity.text = meteo.city
            txvTemperature.text = getString(R.string.temperatureFormat,meteo.temperature)
            txvSky.text = meteo.weather

            // IMAGE
            Glide.with(requireContext()).load(Constants.FLAG_URL.format(meteo.country.lowercase()))
                .into(imvCountry)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val formater =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                binding.txvDateAPI.text =
                    formater.format(Instant.ofEpochSecond(meteo.timestamp.toLong()).atZone(ZoneOffset.UTC).plusSeconds(meteo.timeZone.toLong()))
                binding.txvDatePhone.text = formater.format(Instant.now().atZone(ZoneId.systemDefault()))
            }

            val background = if(meteo.temperature >= 15)
                ContextCompat.getDrawable(requireContext(),R.drawable.warm)
            else
                ContextCompat.getDrawable(requireContext(),R.drawable.cold)

            background!!.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(requireContext(),R.color.semiblack),PorterDuff.Mode.DARKEN)

            ctlMainActivity.background = background
        }
    }
}