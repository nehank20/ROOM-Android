package com.poilkar.nehank.roomandroidteam.model.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User

class UserWithProjects(
    @Embedded val user: User,
    @Relation(
        parentColumn = "empId",
        entityColumn = "projId",
        associateBy = Junction(UsersProjectCrossRef::class)
    )
    val projects : List<Projects>
) {

}