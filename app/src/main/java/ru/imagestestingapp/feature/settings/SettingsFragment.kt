package ru.imagestestingapp.feature.settings

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.imagestestingapp.databinding.FragmentSettingsBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override val bindingProvider: BindingProvider<FragmentSettingsBinding> =
        FragmentSettingsBinding::inflate
    private val viewModel: SettingsViewModel by viewModel()
}