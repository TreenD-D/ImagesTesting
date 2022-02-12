package ru.imagestesting.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}