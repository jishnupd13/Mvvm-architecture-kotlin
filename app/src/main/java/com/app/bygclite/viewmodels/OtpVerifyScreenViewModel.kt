package com.app.bygclite.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bygclite.firebasemodel.UserModel
import com.app.bygclite.repository.AppRepository
import com.app.bygclite.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpVerifyScreenViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    private val dbUsers = FirebaseDatabase.getInstance().getReference(Constants.NODE_USERS)

    private val _user_list = MutableLiveData<List<UserModel>>()
    val userModelList: LiveData<List<UserModel>>
        get() = _user_list

    private val _user_data = MutableLiveData<UserModel>()
    val UserData: MutableLiveData<UserModel>
        get() = _user_data

    private val _calculate_access_token = MutableLiveData<String>()
    val calculateAccessToken: LiveData<String>
        get() = _calculate_access_token

    private val _not_detect = MutableLiveData<String>()
    val notDetect: LiveData<String>
        get() = _not_detect




    fun validateUserData(list: List<UserModel>, mobileNumber: String){
        var isDetected=false

         for (i in list){
             if(i.mobileNumber==mobileNumber){
                 isDetected=true
                 _user_data.postValue(i)
                 break
             }
         }

        if(!isDetected)
            _not_detect.postValue("404")
    }

    fun fetchUserLists() {
        dbUsers.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val users = mutableListOf<UserModel>()
                if (snapshot.exists()) {

                    for (authSnapShot in snapshot.children) {
                        /*val user = authSnapShot.getValue(UserModel::class.java)
                        user?.id = authSnapShot.key
                        user?.let {
                            users.add(it)
                        }*/
                        val user=UserModel(
                            fullname = authSnapShot.child("fullname").getValue(String::class.java),
                            userEmail = authSnapShot.child("userEmail").getValue(String::class.java),
                            userPassword =authSnapShot.child("userPassword").getValue(String::class.java),
                            mobileNumber = authSnapShot.child("mobileNumber").getValue(String::class.java)
                        )
                        user.id=authSnapShot.key
                        users.add(user)
                    }
                    _user_list.postValue(users)
                }else{
                    _user_list.postValue(users)
                }

            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun calculateAccessToken(providerId:String,userId:String,mobileNumber:String){
        _calculate_access_token.postValue(providerId+userId+mobileNumber)
    }

}