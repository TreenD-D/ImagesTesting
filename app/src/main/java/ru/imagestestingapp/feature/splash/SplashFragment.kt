package ru.imagestestingapp.feature.splash

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.imagestestingapp.Screens
import ru.imagestestingapp.databinding.FragmentSplashBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider

private const val ANIMATION_DURATION_MS = 3000L

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by viewModel()

    override var isLightStatusBar = true

    override val bindingProvider: BindingProvider<FragmentSplashBinding> =
        FragmentSplashBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFirstAnimation()
        // activate this if splash background color differs from main window background color
        // view.postDelayed(
        //     { activity?.window?.setBackgroundDrawableResource(R.color.colorBackground) },
        //     ANIMATION_DURATION_MS / 2
        // )

        // Navigate to the next screen
        navigation.newRootFlow(Screens.Flow.patients())
    }

    private fun startFirstAnimation() {
        viewScope.launch {
            delay(ANIMATION_DURATION_MS)
            viewModel.onAnimationEnd()
        }
    }
}