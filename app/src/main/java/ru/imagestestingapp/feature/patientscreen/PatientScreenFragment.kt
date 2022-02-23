package ru.imagestestingapp.feature.patientscreen

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import ru.imagestestingapp.Screens
import ru.imagestestingapp.databinding.FragmentPatientScreenBinding
import ru.imagestestingapp.feature.addpatient.AddPatientFragment
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument
import ru.imagestestingapp.global.utils.viewObserve

class PatientScreenFragment : BaseFragment<FragmentPatientScreenBinding>() {
    override val bindingProvider: BindingProvider<FragmentPatientScreenBinding> =
        FragmentPatientScreenBinding::inflate
    private val viewModel: PatientScreenViewModel by viewModel()

    private val patientId: Long by argument(AddPatientFragment.KEY_PATIENT_ID)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)

        viewModel.getPatientData(patientId)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewObserve(viewModel.patientEntry) {
            binding.lastNameText.text = it.lastName
            binding.nameText.text = it.name
            binding.middleNameText.text = it.thirdName
            binding.birthDateText.text = it.birthDate
        }
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.editIcon.setOnClickListener {
            navigation.navigateTo(Screens.Screen.addPatient(patientId))
        }

        binding.testingButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.testing(patientId))
        }
    }


    companion object {
        const val KEY_PATIENT_ID = "patient_id"
    }

}