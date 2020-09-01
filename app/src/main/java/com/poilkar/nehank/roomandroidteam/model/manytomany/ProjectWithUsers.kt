package com.poilkar.nehank.roomandroidteam.model.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User


class ProjectWithUsers(
    @Embedded val project: Projects,
    @Relation(
        parentColumn = "projId",
        entityColumn = "empId",
        associateBy = Junction(UsersProjectCrossRef::class)
    )
    val users : List<User>
) {

}