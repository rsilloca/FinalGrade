package com.pandita.finalgrade.repository.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pandita.finalgrade.repository.dao.SemesterDao
import com.pandita.finalgrade.repository.models.Semester

class SemesterRepository(private val semesterDao: SemesterDao) {

    val allSemesters: LiveData<List<Semester>> = semesterDao.getAll()

    @WorkerThread
    suspend fun insert(semester: Semester) {
        semesterDao.insert(semester)
    }

    @WorkerThread
    suspend fun update(semester: Semester) {
        semesterDao.update(semester)
    }

}