package com.app.bygclite.repository

import com.app.bygclite.baseresult.safeApiCall
import com.app.bygclite.localdatabaseservice.AppLocalRoomDatabaseDao
import com.app.bygclite.localdatabaseservice.entities.LoginEntity
import com.app.bygclite.localdatabaseservice.entities.StudentEntity
import com.app.bygclite.remoteservice.ApiHelper
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val appLocalRoomDatabaseDao: AppLocalRoomDatabaseDao
) {

    suspend fun getPosts() = safeApiCall { apiHelper.getPosts() }
  //  suspend fun getNestedPosts()= safeApiCall { apiHelper.getNestedPosts() }

    //for room DataBase
    suspend fun insertStudentData(student: StudentEntity) = appLocalRoomDatabaseDao.insert(student)
    suspend fun fetchStudents() = appLocalRoomDatabaseDao.fetch()
    suspend fun createLoginUser(loginEntity: LoginEntity)=appLocalRoomDatabaseDao.createLoginUser(loginEntity)
    suspend fun getUserPhoneNumberIsAlreadyExists(phoneNumber:String)=appLocalRoomDatabaseDao.checkUserAlreadyLogin(phoneNumber)
    suspend fun getUserDetailsUsingMobile(phoneNumber: String)=appLocalRoomDatabaseDao.getDetailsUsingPhoneNumber(phoneNumber)
}