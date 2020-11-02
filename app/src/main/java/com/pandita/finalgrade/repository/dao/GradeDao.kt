package com.pandita.finalgrade.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pandita.finalgrade.repository.models.Grade

@Dao
interface GradeDao {
    @Insert
    fun insert(grade: Grade)

    @Update
    fun update(grade: Grade)

    @Query("SELECT * FROM grade WHERE Status=1")
    fun getAll(): LiveData<List<Grade>>

    @Query("DELETE FROM grade")
    fun clear()

    @Query("SELECT * FROM grade WHERE Id = :key and Status=1")
    fun getGrade(key: Long): LiveData<Grade?>

    @Query("SELECT * FROM grade WHERE IdCourse = :keyCourse and Status=1")
    fun getCourseGrades(keyCourse: Long): LiveData<List<Grade>>
}