package com.app.bygclite.localdatabaseservice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.bygclite.localdatabaseservice.entities.LoginEntity
import com.app.bygclite.localdatabaseservice.entities.StudentEntity

/** Created by Jishnu P Dileep on 27-05-2021 */

@Dao
interface AppLocalRoomDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity): Long

    @Query("select * From student ORDER BY studentId ASC")
    suspend fun fetch(): List<StudentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createLoginUser(loginEntity: LoginEntity):Long

    @Query("select COUNT(*) from login where userPhoneNumber=:phoneNumber")
    suspend fun checkUserAlreadyLogin(phoneNumber:String):Int

    @Query("select * from login where userPhoneNumber=:phoneNumber")
    suspend fun getDetailsUsingPhoneNumber(phoneNumber:String):List<LoginEntity>
}