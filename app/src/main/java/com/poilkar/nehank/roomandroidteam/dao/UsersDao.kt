package com.poilkar.nehank.roomandroidteam.dao

import androidx.room.*
import com.poilkar.nehank.roomandroidteam.model.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE empId = :id")
    fun getSpecificUser(id: Int): User

}