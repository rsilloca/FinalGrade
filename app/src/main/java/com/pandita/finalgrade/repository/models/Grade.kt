package com.pandita.finalgrade.repository.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "grade",
    foreignKeys = [ForeignKey(
        entity = Course::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("IdCourse"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Grade (
    @PrimaryKey(autoGenerate = true)
    val Id: Long,
    val IdCourse: Long,
    val Value: Double = 0.0,
    val Percentage: Int = 0,
    val IsApproved: Int = 0,
    val Status: Int = 1
)