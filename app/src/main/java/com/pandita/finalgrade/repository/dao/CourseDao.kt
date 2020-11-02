package com.pandita.finalgrade.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pandita.finalgrade.repository.models.Course

@Dao
interface CourseDao {
    @Insert
    fun insert(course: Course)

    @Update
    fun update(course: Course)

    @Query("SELECT * FROM course WHERE Status=1")
    fun getAll(): LiveData<List<Course>>

    @Query("DELETE FROM course")
    fun clear()

    @Query("SELECT * FROM course WHERE Id = :key and Status=1")
    fun getCourse(key: Long): LiveData<Course?>

    @Query("SELECT * FROM course WHERE IdSemester = :keySemester and Status=1")
    fun getSemesterCourses(keySemester: Long): LiveData<List<Course>>
}