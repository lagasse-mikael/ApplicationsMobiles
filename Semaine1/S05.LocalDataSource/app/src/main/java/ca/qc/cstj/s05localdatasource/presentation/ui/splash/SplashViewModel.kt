package ca.qc.cstj.s05localdatasource.presentation.ui.splash

import android.app.Application
import androidx.lifecycle.*
import ca.qc.cstj.s05localdatasource.data.repositories.UserRepository
import ca.qc.cstj.s05localdatasource.domain.models.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(application : Application) : AndroidViewModel(application) {

    private var _user = MutableLiveData<User>()
    val user : LiveData<User> get() = _user

    private val userRepo = UserRepository(application.applicationContext)

    init {
        viewModelScope.launch {
            userRepo.user.collect {
                _user.value = it
            }
        }
    }

    fun save(firstName:String,lastName:String,isOnline:Boolean){
        viewModelScope.launch {
            userRepo.save(firstName, lastName, isOnline)
        }
    }
}