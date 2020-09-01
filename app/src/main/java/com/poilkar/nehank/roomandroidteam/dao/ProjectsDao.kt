package com.poilkar.nehank.roomandroidteam.dao

import androidx.room.*
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.manytomany.ProjectWithUsers
import com.poilkar.nehank.roomandroidteam.model.manytomany.UserWithProjects
import com.poilkar.nehank.roomandroidteam.model.manytomany.UsersProjectCrossRef

import com.poilkar.nehank.roomandroidteam.model.onetomany.UserAndProjects

@Dao
interface ProjectsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProject(project: Projects)

    @Delete
    fun deleteProject(project: Projects)

    @Query("SELECT *  FROM Projects")
    fun getAllProjects() : List<Projects>

    @Query("SELECT * FROM projects WHERE projId = :id")
    fun getSpecificProject(id: Int) : Projects

//    @Query("SELECT * FROM projects WHERE projectUserId = :id")
//    fun getSpecificUsersProject(id: Int) : List<Projects>


    // one to many relations
    // Because Room runs the two queries for us under the hood,
    // add the @Transaction annotation, to ensure that this happens atomically.
    @Transaction
    @Query("SELECT * FROM User")
    fun getAllUsersWithProjects() : List<UserAndProjects>


    // one to many relations
    // same query as above, only where clause added
    @Transaction
    @Query("SELECT * FROM User WHERE USER.empId= :id")
    fun getSpecificUserWithProjects(id: Int) : List<UserAndProjects>


    // many to many relations
    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithProjects(): List<UserWithProjects>

    @Transaction
    @Query("SELECT * FROM Projects")
    fun getProjectWithUsers(): List<ProjectWithUsers>


    // cross reference
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsersProjectCrossRef(userProjectCrossRef: UsersProjectCrossRef) :Long

    @Query("SELECT * FROM usersprojectcrossref")
    fun getCrossRefTable() : List<UsersProjectCrossRef>

}