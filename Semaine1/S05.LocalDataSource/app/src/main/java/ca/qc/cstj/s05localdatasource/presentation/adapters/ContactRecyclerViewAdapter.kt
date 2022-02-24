package ca.qc.cstj.s05localdatasource.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.loadFromResource
import ca.qc.cstj.s05localdatasource.databinding.ItemContactBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact

class ContactRecyclerViewAdapter(var contacts: List<Contact>) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ContactRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size

    // Gestion d'un item de la collection (une item)
    // L'equivalent d'une carte
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemContactBinding.bind(view)

        fun bind(contact : Contact) {
            with(binding) {
                userName.text = "${contact.firstName} ${contact.lastName}"
                val userStatusString = if(contact.isOnline) "status_online" else "status_offline"
                userStatus.loadFromResource(userStatusString)
            }
        }
    }
}