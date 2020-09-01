package com.poilkar.nehank.roomandroidteam.model.manytomany

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User

//@Entity(
//    primaryKeys = ["projectId", "empId"]
////    indices = [Index(value = ["eId", "pId"], unique = true)]
//)
//class UsersProjectCrossRef(
//
//    val projectId: Int,
//    val empId: Int
//) {
//
//}



@Entity(
    primaryKeys = ["empId","projId"],
    indices = arrayOf(Index(value = arrayOf("empId", "projId"), unique = true)),
    foreignKeys = arrayOf(
        ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("empId"),
        childColumns = arrayOf("empId")
    ),
        ForeignKey(
            entity = Projects::class,
            parentColumns = arrayOf("projId"),
            childColumns = arrayOf("projId")
        )
    ),
)
class UsersProjectCrossRef (
    val empId: Int,
    val projId: Int
)
{

}
