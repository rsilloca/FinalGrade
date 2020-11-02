package com.pandita.finalgrade.ui.semesters

import android.app.Application
import androidx.lifecycle.*
import com.pandita.finalgrade.repository.FinalGradeDatabase
import com.pandita.finalgrade.repository.models.Semester
import com.pandita.finalgrade.repository.repositories.SemesterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SemestersViewModel(application: Application) : AndroidViewModel(application) {

    private val semesterRepository: SemesterRepository
    private val allSemesters: LiveData<List<Semester>>

    init {
        val semestersDao = FinalGradeDatabase.getDatabase(application).semesterDao()
        semesterRepository = SemesterRepository(semestersDao)
        allSemesters = semesterRepository.allSemesters
    }

    fun insert(semester: Semester) = viewModelScope.launch(Dispatchers.IO) {
        semesterRepository.insert(semester)
    }

    fun getAllSemesters() = allSemesters

}