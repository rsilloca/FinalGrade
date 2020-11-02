package com.pandita.finalgrade.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pandita.finalgrade.repository.models.Semester

@Dao
interface SemesterDao {
    @Insert
    fun insert(semester: Semester)

    @Update
    fun update(semester: Semester)

    @Query("SELECT * FROM semester WHERE Status=1")
    fun getAll(): LiveData<List<Semester>>

    @Query("DELETE FROM semester")
    fun clear()

    @Query("SELECT * FROM semester WHERE IsCurrent=1 and Status=1")
    fun getCurrent(): LiveData<Semester?>

    @Query("SELECT * FROM semester WHERE Id = :key and Status=1")
    fun getSemester(key: Long): LiveData<Semester?>

}