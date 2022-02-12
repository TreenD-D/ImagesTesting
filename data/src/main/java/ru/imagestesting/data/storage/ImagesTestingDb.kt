package ru.imagestesting.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.imagestesting.data.storage.dao.PatientDao
import ru.imagestesting.data.storage.entity.PatientEntity

@Database(
    entities = [
        PatientEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class ImagesTestingDb : RoomDatabase() {
    abstract fun patientsDao(): PatientDao
}