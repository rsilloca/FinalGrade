package com.pandita.finalgrade.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pandita.finalgrade.R
import com.pandita.finalgrade.repository.dao.CourseDao
import com.pandita.finalgrade.repository.dao.GradeDao
import com.pandita.finalgrade.repository.dao.SemesterDao
import com.pandita.finalgrade.repository.models.Course
import com.pandita.finalgrade.repository.models.Grade
import com.pandita.finalgrade.repository.models.Semester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Semester::class,
        Course::class,
        Grade::class
    ],
    version = 1,
    exportSchema = false)
abstract class FinalGradeDatabase : RoomDatabase() {

    abstract fun semesterDao(): SemesterDao
    abstract fun courseDao(): CourseDao
    abstract fun gradeDao(): GradeDao

    companion object {

        @Volatile
        private var INSTANCE: FinalGradeDatabase? = null

        fun getDatabase(
            context: Context
        ): FinalGradeDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinalGradeDatabase::class.java,
                    "final_grade_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun destroy() {
            INSTANCE = null
        }
    }
}