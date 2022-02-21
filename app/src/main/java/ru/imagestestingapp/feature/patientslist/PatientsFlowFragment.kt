package ru.imagestestingapp.feature.patientslist

import ru.imagestestingapp.Screens
import ru.imagestestingapp.global.ui.fragment.FlowFragment

class PatientsFlowFragment : FlowFragment() {
    override val launchScreen = Screens.Screen.patients()
}