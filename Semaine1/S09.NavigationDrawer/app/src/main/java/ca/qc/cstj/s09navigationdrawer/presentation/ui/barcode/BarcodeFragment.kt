package ca.qc.cstj.s09navigationdrawer.presentation.ui.barcode

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentBarcodeBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

class BarcodeFragment : Fragment(R.layout.fragment_barcode) {

    private val binding: FragmentBarcodeBinding by viewBinding()
    private val viewModel: BarcodeViewModel by viewModels()

    private val zxingActivityLauncher : ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract(), ::handleZxingResult)

    private val quickieActivityLauncher = registerForActivityResult(ScanCustomCode(), ::handleQuickieResult)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnZXing.setOnClickListener {
            val options = ScanOptions()

            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            options.setPrompt(getString(R.string.scan_code))
            options.setCameraId(0)

            options.setBeepEnabled(true)

            zxingActivityLauncher.launch(options)
        }

        binding.btnQuickie.setOnClickListener {
            val config = ScannerConfig.build {
                setBarcodeFormats(listOf(BarcodeFormat.FORMAT_CODE_128))
                setHapticSuccessFeedback(true)
                setShowTorchToggle(true)
                setHorizontalFrameRatio(2.2f)
                setUseFrontCamera(false)
            }

            quickieActivityLauncher.launch(config)
        }
    }

    private fun handleZxingResult(result: ScanIntentResult) {
        if(result.contents == null) {
            binding.txvCodeContent.text = getString(R.string.error)
        } else {
            binding.txvCodeContent.text = result.contents
            viewModel.addCheckIn(result.contents)
        }
    }

    private fun handleQuickieResult(result: QRResult) {
        when(result){
            is QRResult.QRSuccess -> {
                binding.txvCodeContent.text = result.content.rawValue
                viewModel.addCheckIn(result.content.rawValue)
            }
            is QRResult.QRUserCanceled -> {
                binding.txvCodeContent.text = getString(R.string.user_canceled)
            }
            is QRResult.QRMissingPermission -> {
                binding.txvCodeContent.text = getString(R.string.missing_permission)
            }
            is QRResult.QRError -> {
                binding.txvCodeContent.text = result.exception.message
            }
            null -> {
                binding.txvCodeContent.text = getString(R.string.error)
            }
        }
    }

}