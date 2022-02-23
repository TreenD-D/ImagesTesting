package ru.imagestesting.data.preference

import ru.imagestesting.domain.model.enums.Language
import com.tfcporciuncula.flow.FlowSharedPreferences
import com.tfcporciuncula.flow.Preference
import kotlinx.coroutines.ExperimentalCoroutinesApi

private const val FIREBASE_TOKEN = "FIREBASE_TOKEN"
private const val AUTH_TOKEN = "AUTH_TOKEN"
private const val WELCOME_SHOWN = "WELCOME_SHOWN"
private const val LANGUAGE = "LANGUAGE"

private const val RANDOMIZE_IMAGES = "RANDOMIZE_IMAGES"
private const val PLAY_SOUND = "PLAY_SOUND"
private const val IMAGE_DURATION = "IMAGE_DURATION"
private const val WAIT_DURATION = "WAIT_DURATION"

@OptIn(ExperimentalCoroutinesApi::class)
class PreferencesWrapper(private val preferences: FlowSharedPreferences) {
    val firebaseToken: Preference<String>
        get() = preferences.getString(FIREBASE_TOKEN)
    val authToken: Preference<String>
        get() = preferences.getString(AUTH_TOKEN)
    val welcomeShown: Preference<Boolean>
        get() = preferences.getBoolean(WELCOME_SHOWN, false)
    val language: Preference<String>
        get() = preferences.getString(LANGUAGE, Language.EN.tag)

    val randomizeImages: Preference<Boolean>
        get() = preferences.getBoolean(RANDOMIZE_IMAGES, false)
    val playSound: Preference<Boolean>
        get() = preferences.getBoolean(PLAY_SOUND, false)
    val imageDuration: Preference<Float>
        get() = preferences.getFloat(IMAGE_DURATION, 2.0f)
    val waitDuration: Preference<Float>
        get() = preferences.getFloat(WAIT_DURATION, 1.0f)

}
