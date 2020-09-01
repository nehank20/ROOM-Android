package com.poilkar.nehank.roomandroidteam.model.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User

class UserAndProjects (
    @Embedded val user: User,
    @Relation(
        parentColumn = "empId",
        entityColumn = "projUserId"
    )
    val listOfProjects: List<Projects>
){
}