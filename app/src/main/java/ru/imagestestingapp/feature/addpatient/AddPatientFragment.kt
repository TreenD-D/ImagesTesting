package ru.imagestestingapp.feature.addpatient

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import pro.appcraft.lib.utils.common.setGone
import pro.appcraft.lib.utils.common.setVisible
import ru.imagestesting.domain.model.patients.PatientEntry
import ru.imagestestingapp.R
import ru.imagestestingapp.databinding.FragmentPatientAddBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument
import ru.imagestestingapp.global.utils.viewObserve
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AddPatientFragment : BaseFragment<FragmentPatientAddBinding>() {
    override val bindingProvider: BindingProvider<FragmentPatientAddBinding> =
        FragmentPatientAddBinding::inflate
    private val viewModel: AddPatientViewModel by viewModel()

    private val patientId: Long? by argument(KEY_PATIENT_ID)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)

        if (patientId == null) {
            binding.deleteButton.setGone()
            binding.saveButton.setText(R.string.add_patient)
        } else {
            viewModel.getPatientData(patientId!!)
            binding.deleteButton.setVisible()
            binding.saveButton.setText(R.string.edit_patient)
        }

        initObservers()
        initListeners()
    }


    private fun initObservers() {
        viewObserve(viewModel.savedPatientEntry) {
            binding.lastnameInputView.setText(it.lastName)
            binding.firstnameInputView.setText(it.name)
            binding.middlenameInputView.setText(it.thirdName)
            binding.birthDateInputView.setText(it.birthDate)
        }
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.saveButton.setOnClickListener {
            if (validateRequiredView(
                    binding.lastnameInputView,
                    binding.lastnameInputLayout
                ) && (binding.birthDateInputView.text.toString()
                    .isBlank() || isValidDate(binding.birthDateInputView.text.toString()))
            ) {
                fragmentScope.launch {
                    commitUserSelectedData()
                    if (patientId != null)
                        viewModel.updatePatient(patientId!!)
                    else
                        viewModel.savePatient()
                    onBackPressed()
                }
            }
        }
        binding.deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.delete_patient_dialog_title)
                .setPositiveButton(
                    R.string.btn_ok
                ) { dialog, _ ->
                    fragmentScope.launch {
                        viewModel.deletePatient(patientId!!)
                        dialog?.dismiss()
                        onBackPressed()
                    }
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        val listener = MaskedTextChangedListener(
            "[00]{.}[00]{.}[9900]",
            binding.birthDateInputView,
            object : MaskedTextChangedListener.ValueListener {
                private var skipValue = true
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    skipValue = false
                }
            })

        binding.birthDateInputView.apply {
            addTextChangedListener(listener)
            addTextChangedListener {
                binding.birthDateInputLayout.error = null
            }
            onFocusChangeListener = listener
        }
    }

    private fun commitUserSelectedData() {
        viewModel.commitUserSelectedInfo(
            PatientEntry(
                id = -1,
                name = binding.firstnameInputView.text.toString(),
                lastName = binding.lastnameInputView.text.toString(),
                thirdName = binding.middlenameInputView.text.toString(),
                birthDate = binding.birthDateInputView.text.toString(),
                linkedImagesIds = ""
            )
        )

    }


    private fun validateRequiredView(
        textview: TextInputEditText,
        layout: TextInputLayout
    ): Boolean {
        return if (textview.text.isNullOrBlank()) {
            layout.error = getString(
                R.string.required_field_error
            )
            false
        } else {
            layout.error = null
            true
        }
    }

    private fun isValidDate(inDate: String): Boolean {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        dateFormat.isLenient = false
        try {
            val date = dateFormat.parse(inDate.trim { it <= ' ' })
            if (date < Calendar.getInstance().time) return true
        } catch (pe: ParseException) {
            binding.birthDateInputLayout.error = getString(R.string.date_error)
            return false
        }
        binding.birthDateInputLayout.error = getString(R.string.date_error)
        return false
    }


    companion object {
        const val KEY_PATIENT_ID = "patient_id"
    }
}