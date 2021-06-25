package com.app.bygclite.localdatabaseservice.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,
    val userPhoneNumber:String?,
    val userPassword:String?,
    val userGeneratedToken:String="",
    val userEmail:String?=""
)