package ca.qc.cstj.s05localdatasource.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cstj.s05localdatasource.data.repositories.ContactRepository
import ca.qc.cstj.s05localdatasource.domain.models.Contact

class MainViewModel : ViewModel() {

    private val contactRepo = ContactRepository()
    private val _contacts = MutableLiveData<List<Contact>>()

    val contacts : LiveData<List<Contact>> get() = _contacts

    init {
        _contacts.value = contactRepo.retrieveAll(12)
    }
}