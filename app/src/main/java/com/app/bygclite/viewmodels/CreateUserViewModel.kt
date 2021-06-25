package com.app.bygclite.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bygclite.firebasemodel.UserModel
import com.app.bygclite.localdatabaseservice.entities.LoginEntity
import com.app.bygclite.repository.AppRepository
import com.app.bygclite.utils.Constants
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    private val _create_user = MutableLiveData<Long>()
    val createUser: LiveData<Long>
        get() = _create_user

    private val _get_user_details = MutableLiveData<List<LoginEntity>>()
    val getUserDetailsLiveData: LiveData<List<LoginEntity>>
        get() = _get_user_details

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<java.lang.Exception?>
        get() = _result


    fun createUser(userModel: UserModel) {
        val dbUsers = FirebaseDatabase.getInstance().getReference(Constants.NODE_USERS)
        userModel.id = dbUsers.push().key
        dbUsers.child(userModel.id!!).setValue(userModel).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.postValue(null)
            } else {
                _result.postValue(it.exception)
            }
        }
    }

}