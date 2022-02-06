package ru.imagestestingapp.feature.splash

import ru.imagestestingapp.Screens
import ru.imagestestingapp.global.ui.fragment.FlowFragment
import com.github.terrakok.cicerone.androidx.AppScreen

class SplashFlowFragment : FlowFragment() {
    override val launchScreen = Screens.Screen.splash()
}
