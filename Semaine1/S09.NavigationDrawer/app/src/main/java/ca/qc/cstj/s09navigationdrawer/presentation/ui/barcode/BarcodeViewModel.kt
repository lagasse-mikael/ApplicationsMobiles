package ca.qc.cstj.s09navigationdrawer.presentation.ui.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.data.repositories.CheckInRepository
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import kotlinx.coroutines.launch

class BarcodeViewModel: ViewModel() {

    private val checkInRepository = CheckInRepository()

    fun addCheckIn(codeContent: String) {
        viewModelScope.launch {
            val checkIn = CheckIn(codeContent,Constants.DOOR)
            checkInRepository.create(checkIn)
        }
    }
}