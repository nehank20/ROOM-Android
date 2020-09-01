package com.poilkar.nehank.roomandroidteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.poilkar.nehank.roomandroidteam.database.AppDatabase
import com.poilkar.nehank.roomandroidteam.util.Charak
import com.poilkar.nehank.roomandroidteam.util.Kedar
import com.poilkar.nehank.roomandroidteam.util.Nehank
import com.poilkar.nehank.roomandroidteam.util.Rohit
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext







class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)



//         info


//        //***************************************************************** ONLY USER TABLE QUERIES
//        getAllUsers()
//        getSpecificUsers(Nehank)
//
//        //********************************************************** ONLY WORKSTATION TABLE QUERIES
//        getAllWorkStations()
//        getSpecificWorkStationById(2)
//        getSpecificWorkStationByUser(Kedar)
//
//
//        //************************************************************** ONLY PROJECT TABLE QUERIES
//        getAllProjects()
//        getSpecificProjects(Charak)
//
//
//        //****************************************************************************** ONE-TO-ONE
//        // one to one relation between user and workstation.
//        // one user can have only one workstation &&
//        // one workstation can have only one user
//        getUsersAndWorkStation()
//
//
//        //***************************************************************************** ONE-TO-MANY
//        // one to many relation table between user and projects
//        // one user can have multiple projects
//        // returns all users with user and project list obj
//        getAllUsersWithProjects()
//
//
//        // one to many relation table between user and projects
//        // one user can have multiple projects
//        // returns specific user and project list obj
//        getSpecificUserWithProjects(Rohit)
//
//
//        //**************************************************************************** MANY-TO-MANY
//        // many to many relation table between user and projects
//        // many user can have multiple projects
//        // returns Arraylist<User, List<Projects>>
//        getUserWithProjects()
//
//        // many to many relation table between user and projects
//        // many projects can have multiple users
//        // returns Arraylist<Projects, List<User>>
//        getProjectWithUsers()
//

        getIntentFromPrevActivity()

    }

    private fun getIntentFromPrevActivity() {
        val data = intent
        val relationType = data.getStringExtra("type")

        when(relationType){

            "tables" ->{
                clQueries.visibility = View.VISIBLE
                val queryNumber = data.getStringExtra("queryNumber")
                when(queryNumber){
                    "1"->{
                        getAllUsers()
                    }
                    "2"->{

                        getAllWorkStations()
                    }
                    "3"->{
                        getAllProjects()
                    }

                }
            }
            "simpleQueries" ->{
                clQueries.visibility = View.VISIBLE
                val queryNumber = data.getStringExtra("queryNumber")
                when(queryNumber){
                    "1"->{
                        val id = data.getStringExtra("id")
                        getSpecificUsers(id.toInt())
                    }
                    "2"->{
                        val id = data.getStringExtra("id")
                        getSpecificWorkStationById(id.toInt())
                    }
                    "3"->{
                        val id = data.getStringExtra("id")
                        getSpecificWorkStationByUser(id.toInt())
                    }
                    "4"->{
                        val id = data.getStringExtra("id")
                        getSpecificProjects(id.toInt())

                    }
                }
            }
            "oneToOne" ->{
                clQueries.visibility = View.VISIBLE
                getUsersAndWorkStation()
            }
            "oneToMany" ->{
                clQueries.visibility = View.VISIBLE
                val queryNumber = data.getStringExtra("queryNumber")
                when(queryNumber){
                    "1"->{
                        getAllUsersWithProjects()
                    }
                    "2"->{
                        val id = data.getStringExtra("id")
                        getSpecificUserWithProjects(id.toInt())
                    }
                }
            }

            "manyToMany" ->{
                clQueries.visibility = View.VISIBLE
                val queryNumber = data.getStringExtra("queryNumber")
                when(queryNumber){
                    "1"->{
                        getCrossRefTable()
                    }
                    "2"->{
                        getUserWithProjects()
                    }
                    "3"->{
                        getProjectWithUsers()
                    }
                }
            }
        }
    }


    //tables




    private fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfUsers = AppDatabase.invoke(this@DetailsActivity).getUsersDao().getAllUsers()

            withContext(Dispatchers.Main) {
                println(listOfUsers)

                for((index, listt) in listOfUsers.withIndex()){
                    displayManyToManyText.append("Column ${index+1}\n")
                    displayManyToManyText.append("Id - ${listt.empId}\nName - ${listt.empName}\nImage - ${listt.empImage}\n\n")
                }

            }
        }
    }

    private fun getAllWorkStations(){
        CoroutineScope(Dispatchers.IO).launch {
            val listOfWorkStations = AppDatabase.invoke(this@DetailsActivity).getWorkStationDao().getAllWorkStations()

            withContext(Dispatchers.Main) {
                println(listOfWorkStations)

                for((index, listt) in listOfWorkStations.withIndex()){
                    displayManyToManyText.append("Column ${index+1}\n")
                    displayManyToManyText.append("Id - ${listt.workStationId}\nName - ${listt.workStationName}\nLocation - ${listt.workStationLocation}\n[F]W_UId - ${listt.workStationUserId}\n\n")
                }
            }
        }
    }

    private fun getAllProjects() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfProjects = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getAllProjects()

            withContext(Dispatchers.Main) {
                println(listOfProjects)

                for((index, listt) in listOfProjects.withIndex()){
                    displayManyToManyText.append("Column ${index+1}\n")
                    displayManyToManyText.append("Id - ${listt.projName}\nName - ${listt.projName}\nDescription - ${listt.projDesc}\n[F]P_UId - ${listt.projUserId}\n\n")
                }
            }
        }
    }






    //simple queries

    private fun getSpecificUsers(employeeId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificUser = AppDatabase.invoke(this@DetailsActivity).getUsersDao().getSpecificUser(employeeId)

            withContext(Dispatchers.Main) {
                println(specificUser)
                displayManyToManyText.append("\nName: ${specificUser.empName}\nId: ${specificUser.empId}\nImage: ${specificUser.empImage}\n\n")
            }
        }
    }

    private fun getSpecificWorkStationById(workStationId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificWorkStationById = AppDatabase.invoke(this@DetailsActivity).getWorkStationDao().getSpecificWorkStationById(workStationId)

            withContext(Dispatchers.Main) {
                println(specificWorkStationById)
                displayManyToManyText.append("\nID: ${specificWorkStationById.workStationId}\nWorkStation Name: ${specificWorkStationById.workStationName}\nWorkStation Location: ${specificWorkStationById.workStationLocation}\n\n")
            }
        }
    }

    private fun getSpecificWorkStationByUser(empId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificWorkStationByUserId = AppDatabase.invoke(this@DetailsActivity).getWorkStationDao().getSpecificWorkStationByUserId(empId)

            withContext(Dispatchers.Main) {
                println(specificWorkStationByUserId)
                displayManyToManyText.append("\nID: ${specificWorkStationByUserId.workStationId}\nWorkStation Name: ${specificWorkStationByUserId.workStationName}\nWorkStation Location: ${specificWorkStationByUserId.workStationLocation}\n\n")
            }
        }
    }

    private fun getSpecificProjects(projectId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificProject = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getSpecificProject(projectId)

            withContext(Dispatchers.Main) {
                println(specificProject)
                displayManyToManyText.append("\nID: ${specificProject.projId}\nProject Name: ${specificProject.projName}\nProject Desc: ${specificProject.projDesc}\n\n")

            }
        }
    }









    //one-to-one
    private fun getUsersAndWorkStation() {
        CoroutineScope(Dispatchers.IO).launch {
            val userAndWorkStation = AppDatabase.invoke(this@DetailsActivity).getWorkStationDao().getUsersAndWorkStation()

            withContext(Dispatchers.Main) {
                println(userAndWorkStation)

                for ((index,list) in userAndWorkStation.withIndex()){
                    displayManyToManyText.append("\nName: ${list.user.empName}\nId: ${list.user.empId}\nImage: ${list.user.empImage}\n\n" )
                    displayManyToManyText.append("\t\t\t\t\t\tWorkStationId: ${list.workStation.workStationId}\n\t\t\t\t\t\tWorkStation Name: ${list.workStation.workStationName}\n" +
                                "\t\t\t\t\t\tWorkStation Location: ${list.workStation.workStationLocation}\n\n")

                    displayManyToManyText.append("\n\n")
                }
            }
        }
    }



    // one-to-many
    //return all users
    private fun getAllUsersWithProjects(){
        CoroutineScope(Dispatchers.IO).launch {
            val usersWithProjects = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getAllUsersWithProjects()

            withContext(Dispatchers.Main) {
                println(usersWithProjects)

                for ((index,list) in usersWithProjects.withIndex()){
                    displayManyToManyText.append("\nName: ${list.user.empName}\nId: ${list.user.empId}\nImage: ${list.user.empImage}\n\n" )
                    for (projects in list.listOfProjects){
                        displayManyToManyText.append("\t\t\t\t\t\tProjectId: ${projects.projId}\n\t\t\t\t\t\tProject Name: ${projects.projName}\n" +
                                "\t\t\t\t\t\tProject Desc: ${projects.projDesc}\n\n")
                    }
                    displayManyToManyText.append("\n\n")
                }

            }
        }
    }
    // return specific user
    private fun getSpecificUserWithProjects(empId :Int){
        CoroutineScope(Dispatchers.IO).launch {
            val usersWithProjects = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getSpecificUserWithProjects(empId)

            withContext(Dispatchers.Main) {
                println(usersWithProjects)

                for ((index,list) in usersWithProjects.withIndex()){
                    displayManyToManyText.append("\nName: ${list.user.empName}\nId: ${list.user.empId}\nImage: ${list.user.empImage}\n\n" )
                    for (projects in list.listOfProjects){
                        displayManyToManyText.append("\t\t\t\t\t\tProjectId: ${projects.projId}\n\t\t\t\t\t\tProject Name: ${projects.projName}\n" +
                                "\t\t\t\t\t\tProject Desc: ${projects.projDesc}\n\n")
                    }
                    displayManyToManyText.append("\n\n")
                }
            }
        }
    }







    // many-to-many



    private fun getCrossRefTable() {
        CoroutineScope(Dispatchers.IO).launch {
            val crossRefTable = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getCrossRefTable()

            withContext(Dispatchers.Main) {
                println(crossRefTable)


                for((index, listt) in crossRefTable.withIndex()){
                    displayManyToManyText.append("Column ${index+1}\n")
                    displayManyToManyText.append("EId - ${listt.empId}\nPId - ${listt.projId}\n\n")
                }

            }
        }
    }




    // return arraylist with <User object && listof Projects>
    private fun getUserWithProjects(){
        CoroutineScope(Dispatchers.IO).launch {
            val userWithProjects = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getUserWithProjects()

            withContext(Dispatchers.Main) {
                println(userWithProjects)

                for ((index,list) in userWithProjects.withIndex()){
                    displayManyToManyText.append("\nName: ${list.user.empName}\nId: ${list.user.empId}\nImage: ${list.user.empImage}\n\n" )
                    for (projects in list.projects){
                        displayManyToManyText.append("\t\t\t\t\t\tProjectId: ${projects.projId}\n\t\t\t\t\t\tProject Name: ${projects.projName}\n" +
                                "\t\t\t\t\t\tProject Desc: ${projects.projDesc}\n\n")
                    }
                    displayManyToManyText.append("\n\n")
                }
            }
        }
    }
    // return arraylist with <Projects object && listof User>
    private fun getProjectWithUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val projectWithUsers = AppDatabase.invoke(this@DetailsActivity).getProjectsDao().getProjectWithUsers()

            withContext(Dispatchers.Main) {
                println(projectWithUsers)

                for ((index,list) in projectWithUsers.withIndex()){
                    displayManyToManyText.append("\nProject ID: ${list.project.projId}\nProject Name: ${list.project.projName}\nProject Desc: ${list.project.projDesc}\n\n" )
                    for (users in list.users){
                        displayManyToManyText.append("\t\t\t\t\t\tNameId: ${users.empId}\n\t\t\t\t\t\tUsers Name: ${users.empName}\n" +
                                "\t\t\t\t\t\tUsers Image: ${users.empImage}\n\n")
                    }
                    displayManyToManyText.append("\n\n")
                }
            }
        }
    }
}