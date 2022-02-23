package ru.imagestestingapp.feature.activeimagering

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import coil.load
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pro.appcraft.lib.utils.common.addSystemWindowInsetToPadding
import pro.appcraft.lib.utils.common.setGone
import pro.appcraft.lib.utils.common.setVisible
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.R
import ru.imagestestingapp.databinding.FragmentActiveImageringBinding
import ru.imagestestingapp.global.ui.fragment.BaseFragment
import ru.imagestestingapp.global.utils.BindingProvider
import ru.imagestestingapp.global.utils.argument

class ActiveImageringFragment : BaseFragment<FragmentActiveImageringBinding>() {
    override val bindingProvider: BindingProvider<FragmentActiveImageringBinding> =
        FragmentActiveImageringBinding::inflate
    private val viewModel: ActiveImageringViewModel by viewModel()

    private val patientId: Long by argument(KEY_PATIENT_ID)
    private val imagesType: ImagesType by argument(KEY_IMAGES_TYPE)

    private var player: MediaPlayer? = null

    private var imageClickedOnce: Boolean = false

    private var isPaused: Boolean = false
    private var currentImageIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        player = MediaPlayer.create(context, R.raw.tone)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addSystemWindowInsetToPadding(top = true, bottom = true)


        viewModel.initTestResources(patientId, imagesType)

        initStartDrawable()
        initObservers()
        initListeners()
    }

    private fun initStartDrawable() {
        when (imagesType) {
            ImagesType.OBJECTS -> {
                binding.image.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.objects_start,
                        null
                    )
                )
            }
            ImagesType.ACTIONS -> {
                binding.image.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.actions_start,
                        null
                    )
                )
            }
        }
    }


    private fun initObservers() {

    }

    private fun initListeners() {
        binding.startButton.setOnClickListener {
            startTest()
        }
        binding.image.setOnClickListener {
            pauseOnDoubleClick()
        }
        binding.stopButton.setOnClickListener {
            onBackPressed()
        }
        binding.continueButton.setOnClickListener {
            continueTest()
        }
    }


    private fun pauseOnDoubleClick() {
        if (imageClickedOnce) {
            pauseTest()
            return
        }
        imageClickedOnce = true
        val exitDuration = resources.getInteger(R.integer.test_doubleclick_duration)
        fragmentScope.launch {
            delay(exitDuration.toLong())
            imageClickedOnce = false
        }
    }

    private fun startTest() {
        viewModel.imagesList.value?.let {
            binding.image.setVisible()
            binding.startButton.setGone()
            fragmentScope.launch {
                if (currentImageIndex == 0) delay((viewModel.savedSettings.value!!.imageDuration * 1000).toLong())
                val images = it.subList(currentImageIndex, it.lastIndex + 1)
                for (image in images) {
                    if (isPaused) {
                        return@launch
                    }
                    binding.image.apply {
                        post {
                            load(image)
                        }
                    }
                    delay((viewModel.savedSettings.value!!.imageDuration * 1000).toLong())
                    if (isPaused) {
                        return@launch
                    }
                    binding.image.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.empty_placeholder,
                            null
                        )
                    )
                    if (viewModel.savedSettings.value!!.isSoundEnabled) {
                        player?.start()
                    }
                    delay((viewModel.savedSettings.value!!.waitDuration * 1000).toLong())
                    if (isPaused) {
                        return@launch
                    }
                    currentImageIndex++
                }
                binding.image.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.test_end,
                        null
                    )
                )
            }
        }
    }


    private fun pauseTest() {
        isPaused = true
        binding.image.setGone()
        binding.continueButton.setVisible()
        binding.stopButton.setVisible()
    }

    private fun continueTest() {
        isPaused = false
        if (viewModel.imagesList.value!!.size > currentImageIndex) startTest()
        binding.continueButton.setGone()
        binding.stopButton.setGone()
        binding.image.setVisible()
    }

    companion object {
        const val KEY_PATIENT_ID = "patient_id"
        const val KEY_IMAGES_TYPE = "images"

    }
}