package ca.qc.cstj.s05localdatasource.presentation.ui.main

import android.app.Application
import androidx.lifecycle.*
import ca.qc.cstj.s05localdatasource.data.AppDatabase
import ca.qc.cstj.s05localdatasource.data.repositories.ContactRepository
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts : LiveData<List<Contact>> get() = _contacts

    private val contactRepo = AppDatabase.getDatabase(application).contactRepository()

    init {
        // collect == await
        viewModelScope.launch {
            contactRepo.retrieveAll().collect {
                _contacts.value = it
            }
        }
    }

    public fun createContact(firstname:String ,lastname:String,isonline:Boolean ) {
        val contact = Contact("Mikael","Lagasse",true)
        viewModelScope.launch {
            contactRepo.create(contact)
        }
    }

    public fun deleteContact(contact : Contact ) {
        viewModelScope.launch {
            contactRepo.delete(contact)
        }
    }

    public fun updateContact(contact : Contact ) {
        viewModelScope.launch {
            contactRepo.update(contact)
        }
    }
}