package ru.imagestestingapp.feature.activeimagering.preselect

import android.os.Bundle
import android.view.View
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.Screens
import ru.imagestestingapp.databinding.FragmentTestTypeSelectBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument

class TestTypeSelectFragment : BaseFragment<FragmentTestTypeSelectBinding>() {
    override val bindingProvider: BindingProvider<FragmentTestTypeSelectBinding> =
        FragmentTestTypeSelectBinding::inflate

    private val patientId: Long by argument(KEY_PATIENT_ID)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)
        initListeners()
    }

    private fun initListeners() {
        binding.objectsSelectButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.activeImagering(patientId, ImagesType.OBJECTS))
        }
        binding.actionsSelectButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.activeImagering(patientId, ImagesType.ACTIONS))
        }

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    companion object {
        const val KEY_PATIENT_ID = "patient_id"
    }
}