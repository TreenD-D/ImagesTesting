package ru.imagestestingapp.feature.settings

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import pro.appcraft.lib.utils.common.setGone
import pro.appcraft.lib.utils.common.setVisible
import ru.imagestesting.domain.model.settings.SettingsState
import ru.imagestestingapp.R
import ru.imagestestingapp.databinding.FragmentSettingsBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.viewObserve

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override val bindingProvider: BindingProvider<FragmentSettingsBinding> =
        FragmentSettingsBinding::inflate
    private val viewModel: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)

        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewObserve(viewModel.savedSettings) {
            binding.randomizeImagesSwitch.isChecked = it.isImageRandomized
            binding.playSoundSwitch.isChecked = it.isSoundEnabled
            binding.mainImageDurationInputView.setText(it.imageDuration.toString())
            binding.waitDurationInputView.setText(it.waitDuration.toString())
        }
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.saveButton.setOnClickListener {
            fragmentScope.launch {
                viewModel.saveSettings(
                    SettingsState(
                        isImageRandomized = binding.randomizeImagesSwitch.isChecked,
                        isSoundEnabled = binding.playSoundSwitch.isChecked,
                        imageDuration = binding.mainImageDurationInputView.text.toString().toFloat(),
                        waitDuration = binding.waitDurationInputView.text.toString().toFloat()
                        )
                )
                onBackPressed()
            }
        }
    }
}