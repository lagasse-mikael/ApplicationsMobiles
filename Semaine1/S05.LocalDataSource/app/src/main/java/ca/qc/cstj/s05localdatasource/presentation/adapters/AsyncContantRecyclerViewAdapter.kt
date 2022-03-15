package ca.qc.cstj.s05localdatasource.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.loadFromResource
import ca.qc.cstj.s05localdatasource.databinding.ItemContactBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact

class AsyncContantRecyclerViewAdapter(private val onContactClick : (Contact) -> Unit )
    : RecyclerView.Adapter<AsyncContantRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context)
            , parent
            , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            onContactClick(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.idContact == oldItem.idContact
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    // root = equivalent de la View.
    inner class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact : Contact) {
            with(binding) {
                userName.text = "${contact.firstName} ${contact.lastName}"
                val userStatusString = if(contact.isOnline) "status_online" else "status_offline"
                userStatus.loadFromResource(userStatusString)
            }
        }
    }
}