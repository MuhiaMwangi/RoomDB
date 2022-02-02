package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//DAO contains methods that are used for accessing  the database
//It is in DAOs that we write our queries

@Dao
interface UserDao{

    @Insert( onConflict = OnConflictStrategy.IGNORE )
    suspend fun addUser(user:User){

    }

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
   //fun getAllUsers(): LiveData<List<User>>


//DAO CLASS
    //@get: Query ("SELECT * FROM user_table ORDER BY id ASC")
    //val allUsers:LiveData<List<User>>

//VIEWMODEL CLASS
    //val allUser:LiveData<List<User>>
    //allUser=UserDao.allUsers

//MAINACTIVITY CLASS
    //UserViewmodel.allUser---THEN observe IT SINCE ITS THE LiveData
}