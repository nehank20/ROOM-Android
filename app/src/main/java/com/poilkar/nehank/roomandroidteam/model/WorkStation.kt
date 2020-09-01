package com.poilkar.nehank.roomandroidteam.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.poilkar.nehank.roomandroidteam.model.User

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("empId"),
        childColumns = arrayOf("workStationUserId"),
        onDelete = CASCADE
    )]
)
class WorkStation (
    @PrimaryKey(autoGenerate = true)
    val workStationId: Int,
    val workStationName: String,
    val workStationLocation: String,
    val workStationUserId :Int
){

}