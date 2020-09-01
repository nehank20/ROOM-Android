package com.poilkar.nehank.roomandroidteam.model.onetoone

import androidx.room.Embedded
import androidx.room.Relation
import com.poilkar.nehank.roomandroidteam.model.User
import com.poilkar.nehank.roomandroidteam.model.WorkStation

class UserAndWorkStation(
    @Embedded val user: User,
    @Relation(
        parentColumn = "empId",
        entityColumn = "workStationUserId"
    )
    val workStation: WorkStation
) {

}