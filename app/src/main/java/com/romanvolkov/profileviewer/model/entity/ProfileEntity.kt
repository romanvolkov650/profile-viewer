package com.romanvolkov.profileviewer.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ProfileEntity(
    val results: List<Profile>
)

@Parcelize
data class Profile(
    val dob: Dob,
    val email: String,
    val name: Name,
    val phone: String,
    val picture: Picture
) : Parcelable

@Parcelize
data class Dob(
    val age: Int,
    val date: String
) : Parcelable

@Parcelize
data class Name(
    val first: String,
    val last: String,
    val title: String
) : Parcelable

@Parcelize
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
) : Parcelable