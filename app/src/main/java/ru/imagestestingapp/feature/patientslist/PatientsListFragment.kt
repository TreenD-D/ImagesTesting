package ru.imagestestingapp.feature.patientslist

import android.os.Bundle
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import ru.imagestestingapp.Screens
import ru.imagestestingapp.databinding.FragmentPatientsListBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.fastAdapter
import ru.imagestestingapp.global.utils.itemAdapter
import ru.imagestestingapp.global.utils.viewObserve

class PatientsListFragment : BaseFragment<FragmentPatientsListBinding>() {
    override val bindingProvider: BindingProvider<FragmentPatientsListBinding> =
        FragmentPatientsListBinding::inflate
    private val viewModel: PatientsListViewModel by viewModel()

    private val itemAdapter: ItemAdapter<PatientItem> by itemAdapter()
    private val fastAdapter: FastAdapter<PatientItem> by fastAdapter { listOf(itemAdapter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)
        bindRecycler()
        initObservers()
        initListeners()
    }

    private fun bindRecycler() {
        binding.patientsListView.adapter = fastAdapter

        fastAdapter.onClickListener =
            { v: View?, _: IAdapter<PatientItem>, item: PatientItem, _: Int ->
                if (v != null) {
                        navigation.navigateTo(Screens.Screen.patientScreen(item.entry.id))
                }
                false
            }
    }

    private fun initObservers() {
        viewObserve(viewModel.patientsList) {
            it?.map(::PatientItem)
                .also { list ->
                    if (list != null)
                        FastAdapterDiffUtil[itemAdapter] =
                            FastAdapterDiffUtil.calculateDiff(itemAdapter, list)
                }
        }
    }

    private fun initListeners() {
        binding.addNewPatientButton.setOnClickListener {
            navigation.navigateTo(Screens.Screen.addPatient(null))
        }
        binding.settingsIcon.setOnClickListener {
            navigation.navigateTo(Screens.Screen.settings())
        }
    }

}