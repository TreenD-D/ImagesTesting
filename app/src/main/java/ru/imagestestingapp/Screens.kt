package ru.imagestestingapp

import android.content.Intent
import android.net.Uri
import ru.imagestestingapp.feature.splash.SplashFlowFragment
import ru.imagestestingapp.feature.splash.SplashFragment
import pro.appcraft.lib.navigation.getFragmentScreen
import com.github.terrakok.cicerone.androidx.ActivityScreen
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.feature.activeimagering.ActiveImageringFragment
import ru.imagestestingapp.feature.activeimagering.preselect.TestTypeSelectFragment
import ru.imagestestingapp.feature.addpatient.AddPatientFragment
import ru.imagestestingapp.feature.imagesselect.ImagesSelectionFragment
import ru.imagestestingapp.feature.patientscreen.PatientScreenFragment
import ru.imagestestingapp.feature.patientslist.PatientsFlowFragment
import ru.imagestestingapp.feature.patientslist.PatientsListFragment
import ru.imagestestingapp.feature.settings.SettingsFragment
import ru.imagestestingapp.feature.testing.TestingFragment

object Screens {
    object Flow {
        fun splash() = SplashFlowFragment::class.getFragmentScreen()

        fun patients() = PatientsFlowFragment::class.getFragmentScreen()
    }

    object Screen {
        fun splash() = SplashFragment::class.getFragmentScreen()

        fun patients() = PatientsListFragment::class.getFragmentScreen()

        fun addPatient(patientId: Long?) = AddPatientFragment::class.getFragmentScreen(
            AddPatientFragment.KEY_PATIENT_ID to patientId
        )

        fun patientScreen(patientId: Long) = PatientScreenFragment::class.getFragmentScreen(
            PatientScreenFragment.KEY_PATIENT_ID to patientId
        )

        fun testing(patientId: Long) = TestingFragment::class.getFragmentScreen(
            TestingFragment.KEY_PATIENT_ID to patientId
        )

        fun settings() = SettingsFragment::class.getFragmentScreen()

        fun selectImages(patientId: Long, imagesType: ImagesType) =
            ImagesSelectionFragment::class.getFragmentScreen(
                ImagesSelectionFragment.KEY_PATIENT_ID to patientId,
                ImagesSelectionFragment.KEY_IMAGES_TYPE to imagesType
            )

        fun testTypeSelect(patientId: Long) = TestTypeSelectFragment::class.getFragmentScreen(
            TestTypeSelectFragment.KEY_PATIENT_ID to patientId
        )

        fun activeImagering(patientId: Long, imagesType: ImagesType) =
            ActiveImageringFragment::class.getFragmentScreen(
                ActiveImageringFragment.KEY_PATIENT_ID to patientId,
                ActiveImageringFragment.KEY_IMAGES_TYPE to imagesType
            )

    }

    // External action intents
    @Suppress("unused")
    object Action {
        @Suppress("unused")
        fun appSettings() = ActivityScreen("actionAppSettings") { context ->
            Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + context.packageName)
            ).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }

        @Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER")
        fun openLink(link: String) = ActivityScreen("actionOpenLink") { context ->
            Intent(
                Intent.ACTION_VIEW
            ).apply {
                data = Uri.parse(link)
            }
        }

        @Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER")
        fun openDial(phone: String) = ActivityScreen("actionOpenDial") { context ->
            Intent(
                Intent.ACTION_DIAL
            ).apply {
                data = Uri.fromParts("tel", phone, null)
            }
        }

        @Suppress("unused")
        fun mailTo(email: String, subject: String) = ActivityScreen("actionMailTo") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_SENDTO
                ).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                },
                context.resources.getString(R.string.write_on_email)
            )
        }

        @Suppress("unused")
        fun shareText(text: String, header: String? = null) =
            ActivityScreen("actionShareText") { context ->
                Intent.createChooser(
                    Intent(
                        Intent.ACTION_SEND
                    ).apply {
                        putExtra(Intent.EXTRA_TEXT, text)
                        putExtra("sms_body", text)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                        type = "text/plain"
                    },
                    header ?: context.resources.getString(R.string.share)
                )
            }

        @Suppress("unused")
        fun shareFile(uri: Uri, mimeType: String) = ActivityScreen("actionShareFile") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_SEND
                ).apply {
                    putExtra(Intent.EXTRA_STREAM, uri)
                    type = mimeType
                },
                context.resources.getString(R.string.share)
            )
        }

        @Suppress("unused")
        fun openFolder(uri: Uri) = ActivityScreen("actionOpenFolder") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_VIEW
                ).apply {
                    setDataAndType(uri, "*/*")
                },
                context.resources.getString(R.string.open_folder)
            )
        }

        @Suppress("unused")
        fun openFile(
            uri: Uri,
            mimeType: String
        ) = ActivityScreen("actionOpenFile") { context ->
            Intent.createChooser(
                Intent(
                    Intent.ACTION_VIEW
                ).apply {
                    setDataAndType(uri, mimeType)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                },
                context.resources.getString(R.string.open_file)
            )
        }
    }
}