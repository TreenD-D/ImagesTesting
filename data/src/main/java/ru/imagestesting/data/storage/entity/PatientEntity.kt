package ru.imagestesting.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val lastName: String,
    val thirdName: String,
    val birthDate: String,
    val linkedImagesIds: String,
    )