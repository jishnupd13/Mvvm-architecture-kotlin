package com.app.bygclite.models

import com.google.gson.annotations.SerializedName

/** Created by Jishnu P Dileep on 28-05-2021 */
data class TestApiNestedModel (
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?,
    val Username:ArrayList<Name>?,

    var isVisible:Boolean?=false
)

data class Name(
    var userName:String?
)