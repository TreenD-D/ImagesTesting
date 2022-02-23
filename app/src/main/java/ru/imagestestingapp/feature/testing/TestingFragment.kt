package ru.imagestestingapp.feature.testing

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.Screens
import ru.imagestestingapp.databinding.FragmentTestingBinding
import ru.imagestestingapp.feature.addpatient.AddPatientViewModel
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument


class TestingFragment : BaseFragment<FragmentTestingBinding>() {
    override val bindingProvider: BindingProvider<FragmentTestingBinding> =
        FragmentTestingBinding::inflate
    private val viewModel: AddPatientViewModel by viewModel()

    private val patientId: Long by argument(KEY_PATIENT_ID)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)


        initObservers()
        initListeners()
    }

    private fun initObservers() {

    }

    private fun initListeners() {
        binding.actionsSelectButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.selectImages(patientId = patientId, imagesType = ImagesType.ACTIONS))
        }

        binding.objectsSelectButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.selectImages(patientId = patientId, imagesType = ImagesType.OBJECTS))
        }

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    companion object {
        const val KEY_PATIENT_ID = "patient_id"
    }
}