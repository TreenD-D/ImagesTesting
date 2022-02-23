package ru.imagestesting.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.imagestesting.data.storage.entity.PatientEntity

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients ORDER BY lastName DESC")
    fun observeAllEntities(): Flow<List<PatientEntity>>

    @Query("SELECT * FROM patients WHERE id=:id LIMIT 1")
    suspend fun getEntity(id: Long): PatientEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(user: PatientEntity): Long

    @Query("DELETE FROM patients")
    suspend fun drop()

    @Query("DELETE FROM patients WHERE id=:id")
    fun deleteSinglePatient(id: Long)

    @Update
    suspend fun update(patientEntity: PatientEntity)

    @Query("SELECT objectsIds FROM patients WHERE id=:id")
    suspend fun getObjects(id: Long): String

    @Query("SELECT actionsIds FROM patients WHERE id=:id")
    suspend fun getActions(id: Long): String

    @Query("UPDATE patients SET objectsIds=:objects WHERE id=:id")
    suspend fun updateObjects(objects: String, id: Long)

    @Query("UPDATE patients SET actionsIds=:actions WHERE id=:id")
    suspend fun updateActions(actions: String, id: Long)
}