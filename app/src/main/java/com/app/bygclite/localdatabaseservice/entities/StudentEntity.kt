package com.app.bygclite.localdatabaseservice.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Created by Jishnu P Dileep on 27-05-2021 */

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0L,
    val fName: String,
    val lName: String,
    val standard: String,
    val age: Int
)