package ca.qc.cstj.s05localdatasource.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    private lateinit var contactRecyclerViewAdapter : ContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        binding.recyclerViewContact.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContact.adapter = contactRecyclerViewAdapter

        viewModel.contacts.observe(this) {
            contactRecyclerViewAdapter.contacts = it
            contactRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}