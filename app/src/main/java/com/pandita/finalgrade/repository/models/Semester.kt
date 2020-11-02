package com.pandita.finalgrade.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandita.finalgrade.R

@Entity(tableName = "semester")
data class Semester (
    @PrimaryKey(autoGenerate = true)
    val Id: Long,
    val Name: String = "Semester",
    val Description: String = "Description",
    val Year: Int = 2020,
    val IsCurrent: Int = 1,
    val bgColor: Int = R.color.colorPrimary,
    val Status: Int = 1
)