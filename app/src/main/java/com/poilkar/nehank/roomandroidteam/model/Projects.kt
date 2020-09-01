package com.poilkar.nehank.roomandroidteam.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
//    foreignKeys = [ForeignKey(
//        entity = User::class,
//        parentColumns = arrayOf("empId"),
//        childColumns = arrayOf("projectId"),
//        onDelete = CASCADE
//    )]
)
class Projects (
    @PrimaryKey
    val projId: Int,
    val projName: String,
    val projDesc: String,
    val projUserId :Int
){

}