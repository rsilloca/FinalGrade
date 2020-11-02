package com.pandita.finalgrade.repository.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pandita.finalgrade.repository.dao.CourseDao
import com.pandita.finalgrade.repository.models.Course

class CourseRepository(private val courseDao: CourseDao) {

    val allCourses: LiveData<List<Course>> = courseDao.getAll()

    @WorkerThread
    suspend fun insert(course: Course) {
        courseDao.insert(course)
    }

    @WorkerThread
    suspend fun update(course: Course) {
        courseDao.update(course)
    }

}