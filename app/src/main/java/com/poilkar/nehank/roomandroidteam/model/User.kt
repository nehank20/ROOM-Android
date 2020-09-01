package com.poilkar.nehank.roomandroidteam.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    val empId : Int,
    val empName : String,
    val empImage: String
) {


}