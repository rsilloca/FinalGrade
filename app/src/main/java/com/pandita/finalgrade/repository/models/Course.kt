package com.pandita.finalgrade.repository.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pandita.finalgrade.R

@Entity(
    tableName = "course",
    foreignKeys = [ForeignKey(
        entity = Semester::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("IdSemester"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Course (
    @PrimaryKey(autoGenerate = true)
    val Id: Long,
    val IdSemester: Long,
    val Name: String = "Course",
    val Description: String = "Description",
    val Teacher: String = "Teacher",
    val bgColor: Int = R.color.colorPrimary,
    val Status: Int = 1
)