package com.example.vishalsinghcricbuzzcodelab_5.db

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CricketPlayerData(
    val id:Int? =0,
    val photo: Bitmap,
    val name:String,
    val team:String,
    val country:String,
    val dob:String,
    val batter:Boolean,
    val bowler:Boolean,
    val match:Int,
    val run:Int,
    val wicket:Int,
    var favourite:Boolean
) : Parcelable