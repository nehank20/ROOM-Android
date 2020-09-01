package com.poilkar.nehank.roomandroidteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.poilkar.nehank.roomandroidteam.database.AppDatabase
import com.poilkar.nehank.roomandroidteam.model.Projects
import com.poilkar.nehank.roomandroidteam.model.User
import com.poilkar.nehank.roomandroidteam.model.WorkStation
import com.poilkar.nehank.roomandroidteam.model.manytomany.UsersProjectCrossRef
import com.poilkar.nehank.roomandroidteam.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickListeners()


        //***************************************************************************** INSERT DATA
        // insert data into tables
        insertDataOfAllTables()




    }

    private fun showError(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    private fun clickListeners() {

        // tables
        btnUserTable.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","tables")
            intent.putExtra("queryNumber","1")
            startActivity(intent)
        }

        btnWorkStationTable.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","tables")
            intent.putExtra("queryNumber","2")
            startActivity(intent)
        }

        btnProjectTable.setOnClickListener {

            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","tables")
            intent.putExtra("queryNumber","3")
            startActivity(intent)
        }



        //simple Queries
        btnDetailsOfUser.setOnClickListener {
            if(etDetailsOfUser.text.isNullOrEmpty()){
                showError("Please enter a value")
            }else{
                if(etDetailsOfUser.text.toString().trim().toInt() in 1..4){
                    val intent = Intent(this,DetailsActivity::class.java)
                    intent.putExtra("type","simpleQueries")
                    intent.putExtra("queryNumber","1")
                    intent.putExtra("id",etDetailsOfUser.text.toString().trim())
                    startActivity(intent)
                }else{
                    showError("Please enter a number between 1 to 4")
                }
            }
        }

        btnDetailsOfWorkStationByWID.setOnClickListener {
            if(etDetailsOfWorkStationByWID.text.isNullOrEmpty()){
                showError("Please enter a value")
            }else{
                if(etDetailsOfWorkStationByWID.text.toString().trim().toInt() in 1..4){
                    val intent = Intent(this,DetailsActivity::class.java)
                    intent.putExtra("type","simpleQueries")
                    intent.putExtra("queryNumber","2")
                    intent.putExtra("id",etDetailsOfWorkStationByWID.text.toString().trim())
                    startActivity(intent)
                }else{
                    showError("Please enter a number between 1 to 4")
                }
            }
        }

        btnDetailsOfWorkStationByEID.setOnClickListener {
            if(etDetailsOfWorkStationByEID.text.isNullOrEmpty()){
                showError("Please enter a value")
            }else{
                if(etDetailsOfWorkStationByEID.text.toString().trim().toInt() in 1..4){
                    val intent = Intent(this,DetailsActivity::class.java)
                    intent.putExtra("type","simpleQueries")
                    intent.putExtra("queryNumber","3")
                    intent.putExtra("id",etDetailsOfWorkStationByEID.text.toString().trim())
                    startActivity(intent)
                }else{
                    showError("Please enter a number between 1 to 4")
                }
            }

        }

        btnDetailsOfProjectByID.setOnClickListener {
            if(etDetailsOfProjectByID.text.isNullOrEmpty()){
                showError("Please enter a value")
            }else{
                if(etDetailsOfProjectByID.text.toString().trim().toInt() in 1..10){
                    val intent = Intent(this,DetailsActivity::class.java)
                    intent.putExtra("type","simpleQueries")
                    intent.putExtra("queryNumber","4")
                    intent.putExtra("id",etDetailsOfProjectByID.text.toString().trim())
                    startActivity(intent)
                }else{
                    showError("Please enter a number between 1 to 10")
                }
            }
        }





        //one-to-one
        btnOneToOne.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","oneToOne")
            startActivity(intent)
        }




        //one-to-many
        btnOneToManyFirst.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","oneToMany")
            intent.putExtra("queryNumber","1")
            startActivity(intent)
        }

        btnOneToManySecond.setOnClickListener {

            if(etUserID.text.isNullOrEmpty()){
                showError("Please enter a value")
            }else{
                if(etUserID.text.toString().trim().toInt() == 1 || etUserID.text.toString().trim().toInt() == 2 ||
                        etUserID.text.toString().trim().toInt() == 3 || etUserID.text.toString().trim().toInt() == 4 ){
                    val intent = Intent(this,DetailsActivity::class.java)
                    intent.putExtra("type","oneToMany")
                    intent.putExtra("queryNumber","2")
                    intent.putExtra("id",etUserID.text.toString().trim())
                    startActivity(intent)
                }else{
                    showError("Please enter a number between 1 to 4")
                }
            }
        }




        //many-to-many

        btnCrossRefTable.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","manyToMany")
            intent.putExtra("queryNumber","1")
            startActivity(intent)
        }


        btnManyToManyFirst.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","manyToMany")
            intent.putExtra("queryNumber","2")
            startActivity(intent)
        }

        btnManyToManySecond.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("type","manyToMany")
            intent.putExtra("queryNumber","3")
            startActivity(intent)
        }

    }


    private fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfUsers = AppDatabase.invoke(this@MainActivity).getUsersDao().getAllUsers()

            withContext(Dispatchers.Main) {
                println(listOfUsers)
            }
        }
    }
    private fun getSpecificUsers(employeeId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificUser = AppDatabase.invoke(this@MainActivity).getUsersDao().getSpecificUser(employeeId)

            withContext(Dispatchers.Main) {
                println(specificUser)
            }
        }
    }


    private fun getAllWorkStations(){
        CoroutineScope(Dispatchers.IO).launch {
            val listOfWorkStations = AppDatabase.invoke(this@MainActivity).getWorkStationDao().getAllWorkStations()

            withContext(Dispatchers.Main) {
                println(listOfWorkStations)
            }
        }
    }
    private fun getSpecificWorkStationById(workStationId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificWorkStationById = AppDatabase.invoke(this@MainActivity).getWorkStationDao().getSpecificWorkStationById(workStationId)

            withContext(Dispatchers.Main) {
                println(specificWorkStationById)
            }
        }
    }
    private fun getSpecificWorkStationByUser(empId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificWorkStationByUserId = AppDatabase.invoke(this@MainActivity).getWorkStationDao().getSpecificWorkStationByUserId(empId)

            withContext(Dispatchers.Main) {
                println(specificWorkStationByUserId)
            }
        }
    }

    // one-to-one
    private fun getUsersAndWorkStation() {
        CoroutineScope(Dispatchers.IO).launch {
            val userAndWorkStation = AppDatabase.invoke(this@MainActivity).getWorkStationDao().getUsersAndWorkStation()

            withContext(Dispatchers.Main) {
                println(userAndWorkStation)
            }
        }
    }

    private fun getAllProjects() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfProjects = AppDatabase.invoke(this@MainActivity).getProjectsDao().getAllProjects()

            withContext(Dispatchers.Main) {
                println(listOfProjects)
            }
        }
    }
    private fun getSpecificProjects(projectId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val specificProject = AppDatabase.invoke(this@MainActivity).getProjectsDao().getSpecificProject(projectId)

            withContext(Dispatchers.Main) {
                println(specificProject)
            }
        }
    }

    // one-to-many
    //return all users
    private fun getAllUsersWithProjects(){
        CoroutineScope(Dispatchers.IO).launch {
            val usersWithProjects = AppDatabase.invoke(this@MainActivity).getProjectsDao().getAllUsersWithProjects()

            withContext(Dispatchers.Main) {
                println(usersWithProjects)
            }
        }
    }
    // return specific user
    private fun getSpecificUserWithProjects(empId :Int){
        CoroutineScope(Dispatchers.IO).launch {
            val usersWithProjects = AppDatabase.invoke(this@MainActivity).getProjectsDao().getSpecificUserWithProjects(empId)

            withContext(Dispatchers.Main) {
                println(usersWithProjects)
            }
        }
    }


    // many-to-many
    // return arraylist with <User object && listof Projects>
    private fun getUserWithProjects(){
        CoroutineScope(Dispatchers.IO).launch {
            val userWithProjects = AppDatabase.invoke(this@MainActivity).getProjectsDao().getUserWithProjects()

            withContext(Dispatchers.Main) {
                println(userWithProjects)
            }
        }
    }
    // return arraylist with <Projects object && listof User>
    private fun getProjectWithUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val projectWithUsers = AppDatabase.invoke(this@MainActivity).getProjectsDao().getProjectWithUsers()

            withContext(Dispatchers.Main) {
                println(projectWithUsers)
            }
        }
    }


    //insert data in db onCreate()
    private fun insertDataOfAllTables() {


        progressBar.visibility = View.VISIBLE
        insertUsersData()
        runBlocking {
            delay(1000)
        }
        insertWorkStationData()
        runBlocking {
            delay(1000)
        }
        insertProjectsData()
        runBlocking {
            delay(1000)
        }
        insertCrossReferenceData()
        runBlocking {
            delay(1000)
        }
        progressBar.visibility = View.GONE


    }

    private fun insertUsersData(){
        val user1 = User(Nehank, "Nehank", "ImageUrl")
        val user2 = User(Yash, "Yash", "ImageUrl")
        val user3 = User(Rohit, "Rohit", "ImageUrl")
        val user4 = User(Kedar, "Kedar", "ImageUrl")

        // insert users
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.invoke(this@MainActivity).getUsersDao().insertUser(user1)
            AppDatabase.invoke(this@MainActivity).getUsersDao().insertUser(user2)
            AppDatabase.invoke(this@MainActivity).getUsersDao().insertUser(user3)
            AppDatabase.invoke(this@MainActivity).getUsersDao().insertUser(user4)

        }
    }
    private fun insertProjectsData(){

        val project1 = Projects(SAPBarcode, "SAPBarcode", "Barcode Scanning app", Nehank)

        val project2 = Projects(Charak, "Charak", "Doctor finding app", Yash)
        val project3 = Projects(SAPRFID, "SAP RFID", "RFID scanning app", Yash)

        val project4 = Projects(TownTalk, "Towntalk", "Marathi news app", Kedar)
        val project5 = Projects(Circle, "Circle", "Money sharing app", Kedar)
        val project6 = Projects(Accure, "Accure", "Gaming app", Kedar)

        val project7 = Projects(FutureReady, "Future Ready", "Loan app", Rohit)
        val project8 = Projects(Foogo, "Foogo", "Food Delivery app", Rohit)
        val project9 = Projects(Regards, "Regards", "Augmented reality app", Rohit)
        val project10 = Projects(Drabble, "Drabble", "Content writers app", Rohit)

        // insert projects
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project1)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project2)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project3)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project4)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project5)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project6)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project7)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project8)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project9)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertProject(project10)

        }
    }
    private fun insertWorkStationData(){
        val workStation1 = WorkStation(1,"Yash's Workstation","Middle-Right Row 2", Yash)
        val workStation2 = WorkStation(2,"Kedar's Workstation","Middle-Left Row 2", Kedar)
        val workStation3 = WorkStation(3,"Rohit's Workstation","End-Left Row 1", Rohit)
        val workStation4 = WorkStation(4,"Nehank's Workstation","Middle-Right Row 1", Nehank)

        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.invoke(this@MainActivity).getWorkStationDao().insertWorkStation(workStation1)
            AppDatabase.invoke(this@MainActivity).getWorkStationDao().insertWorkStation(workStation2)
            AppDatabase.invoke(this@MainActivity).getWorkStationDao().insertWorkStation(workStation3)
            AppDatabase.invoke(this@MainActivity).getWorkStationDao().insertWorkStation(workStation4)
        }
    }
    private fun insertCrossReferenceData(){


        //primary people
        val crossRef1 = UsersProjectCrossRef(Nehank, SAPBarcode)

        val crossRef2 = UsersProjectCrossRef(Yash, Charak)
        val crossRef3 = UsersProjectCrossRef(Yash, SAPRFID)

        val crossRef4 = UsersProjectCrossRef(Rohit, FutureReady)
        val crossRef5 = UsersProjectCrossRef(Rohit, Foogo)
        val crossRef6 = UsersProjectCrossRef(Rohit, Regards)
        val crossRef7 = UsersProjectCrossRef(Rohit, Drabble)

        val crossRef8 = UsersProjectCrossRef(Kedar, TownTalk)
        val crossRef9 = UsersProjectCrossRef(Kedar, Circle)
        val crossRef10 = UsersProjectCrossRef(Kedar, Accure)

        // add on people
        val crossRef11 = UsersProjectCrossRef(Rohit, SAPBarcode)
        val crossRef12 = UsersProjectCrossRef(Kedar, Charak)
        val crossRef13 = UsersProjectCrossRef(Kedar, SAPRFID)
        val crossRef14 = UsersProjectCrossRef(Yash, FutureReady)
        val crossRef15 = UsersProjectCrossRef(Nehank, Foogo)
        val crossRef16 = UsersProjectCrossRef(Yash, Accure)


        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef1)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef2)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef3)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef4)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef5)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef6)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef7)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef8)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef9)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef10)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef11)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef12)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef13)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef14)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef15)
            AppDatabase.invoke(this@MainActivity).getProjectsDao().insertUsersProjectCrossRef(crossRef16)



        }

    }





}