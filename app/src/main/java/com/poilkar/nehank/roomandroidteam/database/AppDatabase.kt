package com.poilkar.nehank.roomandroidteam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.poilkar.nehank.roomandroidteam.dao.ProjectsDao
import com.poilkar.nehank.roomandroidteam.dao.UsersDao
import com.poilkar.nehank.roomandroidteam.dao.WorkStationDao
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User
import com.poilkar.nehank.roomandroidteam.model.manytomany.UsersProjectCrossRef
import com.poilkar.nehank.roomandroidteam.model.WorkStation

@Database( entities = [User::class, Projects::class, WorkStation::class, UsersProjectCrossRef::class], version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUsersDao() : UsersDao
    abstract fun getProjectsDao() : ProjectsDao
    abstract fun getWorkStationDao() : WorkStationDao

    companion object{

        @Volatile
        private var instance : AppDatabase? =null
        private val LOCK = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "users_db.db")
                .fallbackToDestructiveMigration()
                .build()



    }




}