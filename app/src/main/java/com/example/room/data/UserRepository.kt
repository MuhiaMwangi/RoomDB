package com.example.room.data

import androidx.lifecycle.LiveData

//A userRepository abstracts access to multiple data sources
//Its a best practice for code separation and clean architecture
class UserRepository(private val userDao:UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

     suspend fun addUser(user:User){
         userDao.addUser(user)
     }
}