package com.poilkar.nehank.roomandroidteam.dao

import androidx.room.*
import com.poilkar.nehank.roomandroidteam.model.onetoone.UserAndWorkStation
import com.poilkar.nehank.roomandroidteam.model.WorkStation

@Dao
interface WorkStationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWorkStation(workStation: WorkStation)

    @Query("SELECT * FROM WorkStation")
    fun getAllWorkStations(): List<WorkStation>

    @Query("SELECT * FROM WorkStation WHERE workStationId= :id")
    fun getSpecificWorkStationById(id: Int): WorkStation

    @Query("SELECT * FROM WorkStation WHERE workStationUserId= :id")
    fun getSpecificWorkStationByUserId(id: Int): WorkStation

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersAndWorkStation(): List<UserAndWorkStation>
}