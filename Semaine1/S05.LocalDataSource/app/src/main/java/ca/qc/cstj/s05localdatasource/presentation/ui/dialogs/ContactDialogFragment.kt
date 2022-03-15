package ca.qc.cstj.s05localdatasource.presentation.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ContactDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val contactName = arguments?.getString("CONTACT_NAME")
        val contactLastName = arguments?.getString("CONTACT_LASTNAME")
        val contactState = arguments?.getBoolean("CONTACT_ONLINE_STATE")

        return AlertDialog.Builder(requireContext())
            .setTitle("Informations")
            .setMessage("${contactName} ${contactLastName} est online? ${contactState.toString()}")
            .setPositiveButton("OK"){_, _ -> }.create()
    }

    // Si on veux ne pas a avoir a mettre de key.
//    companion object {
//        const val CONTACT_NAME = "CONTACT_NAME"
//        const val CONTACT_DIALOG_TAG = "CONTACT_DIALOG_TAG"
//    }
}