package ru.imagestestingapp.feature.imagesselect

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISelectionListener
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.getSelectExtension
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.databinding.FragmentImagesSelectionBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument
import ru.imagestestingapp.global.utils.fastAdapter
import ru.imagestestingapp.global.utils.itemAdapter
import ru.imagestestingapp.global.utils.viewObserve

class ImagesSelectionFragment : BaseFragment<FragmentImagesSelectionBinding>() {
    override val bindingProvider: BindingProvider<FragmentImagesSelectionBinding> =
        FragmentImagesSelectionBinding::inflate
    private val viewModel: ImagesSelectionViewModel by viewModel()

    private val imagesType: ImagesType by argument(KEY_IMAGES_TYPE)
    private val patientId: Long by argument(KEY_PATIENT_ID)

    private val itemAdapter: ItemAdapter<ImageItem> by itemAdapter()
    private val fastAdapter: FastAdapter<ImageItem> by fastAdapter { listOf(itemAdapter) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)

        val assetsManager: AssetManager = requireContext().assets
        val path = if (imagesType == ImagesType.ACTIONS) "actions" else "objects"
        val filelist = assetsManager.list(path)!!.toList()
        viewModel.submitImagesList(filelist, patientId, imagesType)

        bindRecycler()
        initObservers()
        initListeners()

    }

    private fun initObservers() {
        viewObserve(viewModel.imagesList){
            it
                .map(::ImageItem)
                .map { item ->
                    if(imagesType == ImagesType.OBJECTS) item.imageType = ImagesType.OBJECTS else item.imageType = ImagesType.ACTIONS
                    item.isSelected = viewModel.selectedImagesList.value?.contains(item.entry) == true
                    item
                }
                .also { list ->
                    FastAdapterDiffUtil[itemAdapter] =
                        FastAdapterDiffUtil.calculateDiff(itemAdapter, list)
                }
        }
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.saveButton.setOnClickListener {
            fragmentScope.launch {
                val selected = fastAdapter.getSelectExtension().selectedItems
                val selectedList = mutableListOf<String>()
                for (item in selected) {
                    selectedList.add(item.entry)
                }
                viewModel.saveSelectedImages(patientId, imagesType, selectedList)
                onBackPressed()
            }
        }
    }

    private fun bindRecycler() {
        val selectExtension = fastAdapter.getSelectExtension()
        selectExtension.apply {
            isSelectable = true
            multiSelect = true
            selectOnLongClick = false
            selectWithItemUpdate = true
        }

        binding.imagesList.adapter = fastAdapter


    }

    companion object {
        const val KEY_PATIENT_ID = "patient_id"
        const val KEY_IMAGES_TYPE = "images"
    }
}