package ca.qc.cstj.s06remotedatasource.presentation.adapters

import android.util.Log
import android.util.Log.ASSERT
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s06remotedatasource.R
import ca.qc.cstj.s06remotedatasource.databinding.ItemPlanetBinding
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import com.bumptech.glide.Glide

class PlanetRecyclerViewAdapter(var planets: List<Planet> = listOf()) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemPlanetBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PlanetRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount() = planets.size


    inner class ViewHolder(private val binding: ItemPlanetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet : Planet){
            binding.txvPlanetName.text = planet.name

//          Pour avoir accees aux images retourner dans notre reponse.
            Log.i(planet.name,planet.image)
            Glide.with(binding.root.context)
                .load(planet.image)
                .into(binding.imgImagePlanet)
        }
    }
}