package com.app.bygclite.firebasemodel

import com.google.firebase.database.Exclude

data class UserModel(
    @Exclude
    var id:String?=null,
    var fullname:String?=null,
    var mobileNumber:String?=null,
    var userToken:String?=null,
    var userEmail:String?=null,
    var userPassword:String?=null
)
