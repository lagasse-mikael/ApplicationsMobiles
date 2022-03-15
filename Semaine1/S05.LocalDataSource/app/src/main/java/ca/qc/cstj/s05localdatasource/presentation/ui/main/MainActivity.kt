package ca.qc.cstj.s05localdatasource.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.notifyAllItemChanged
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import ca.qc.cstj.s05localdatasource.presentation.adapters.AsyncContantRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.ui.dialogs.ContactDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    private lateinit var contactRecyclerViewAdapter : AsyncContantRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        contactRecyclerViewAdapter = AsyncContantRecyclerViewAdapter(::onContactItemClick)
        binding.recyclerViewContact.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContact.adapter = contactRecyclerViewAdapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val contact = contactRecyclerViewAdapter.differ.currentList[viewHolder.adapterPosition]

                when(direction) {
                    ItemTouchHelper.RIGHT -> {
                        viewModel.deleteContact(contact)
                        contactRecyclerViewAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                    ItemTouchHelper.LEFT -> {
                        contact.firstName = "FIRST_NAME"
                        contact.lastName = "LAST_NAME"
                        contact.isOnline = !contact.isOnline
                        viewModel.updateContact(contact)
                        contactRecyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                }
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewContact)

        viewModel.contacts.observe(this) {
            contactRecyclerViewAdapter.differ.submitList(it)
            // fait a l'interieur du differ
            // contactRecyclerViewAdapter.notifyAllItemChanged()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.createContact("Mikael","Lagasse",true)
        }
    }

    private fun onContactItemClick(contact: Contact) {
        //Toast.makeText(this,"${contact.firstName} ${contact.lastName}",Toast.LENGTH_LONG).show()
        val args = Bundle()
        args.putString("CONTACT_NAME",contact.firstName)
        args.putString("CONTACT_LASTNAME",contact.lastName)
        args.putBoolean("CONTACT_ONLINE_STATE",contact.isOnline)

        ContactDialogFragment().apply {
            arguments = args
        }.show(supportFragmentManager,"CONTACT_DIALOG_TAG")

    }

    companion object {
        fun newIntent(context:Context) : Intent {
            return Intent(context,MainActivity::class.java)
        }
    }
}