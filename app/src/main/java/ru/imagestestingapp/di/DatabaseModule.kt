package ru.imagestestingapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.imagestesting.data.storage.ImagesTestingDb

internal val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                androidContext(),
                ImagesTestingDb::class.java,
                "imagestesting.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<ImagesTestingDb>().patientsDao() }
}