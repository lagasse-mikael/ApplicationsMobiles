package ca.mikk.s04recyclerview.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.mikk.s04recyclerview.R
import ca.mikk.s04recyclerview.domain.models.Planet

class PlanetRecyclerViewAdapter(val planets: List<Planet>) :
    RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // planets[position]
    }

    override fun getItemCount(): Int = planets.size

    // Gestion d'un item de la collection (une item)
    // L'equivalent d'une carte.
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

}