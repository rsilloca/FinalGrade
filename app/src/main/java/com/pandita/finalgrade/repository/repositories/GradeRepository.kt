package com.pandita.finalgrade.repository.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pandita.finalgrade.repository.dao.GradeDao
import com.pandita.finalgrade.repository.models.Grade

class GradeRepository(private val gradeDao: GradeDao) {

    val allGrades: LiveData<List<Grade>> = gradeDao.getAll()

    @WorkerThread
    suspend fun insert(grade: Grade) {
        gradeDao.insert(grade)
    }

    @WorkerThread
    suspend fun update(grade: Grade) {
        gradeDao.update(grade)
    }

}