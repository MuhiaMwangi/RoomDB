package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database( entities = [User::class],version=1, exportSchema =false)
abstract  class UserDatabase: RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
        //set INSTANCE to 'volatile' meaning that 'writes' to "this field" --(INSTANCE variable)-- are immediately made visible   to other threads
        @Volatile
        private var INSTANCE: UserDatabase ?= null

        fun getDatabase(context: Context): UserDatabase {
            val userInstance = INSTANCE

            //tempInstance =userInstance
            if ( userInstance != null) {
                //if there is an existing userInstance(i.e userInstance is not null), return that userInstance instead
                return userInstance
            }
//Code in synchronized block is protected from concurrent execution by multiple threads
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

//Different way to implement the Database

/*
@Database(entities= [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    //Associate Database with DAO
    abstract fun userDao(): UserDao

    //create a Database and initialize an instance of that database
    companion object {
        //A database instance
        private var userInstance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase? {

            //check if userInstance --which represents our database instance--is null
            //if its null, we create a new instance of the database
            if (userInstance==null){
                //synchronized: used to create a log object to ensure thread safety
                // it ensures that room DB instance is used in a single thread
                synchronized(UserDatabase::class.java){

                    if (userInstance==null){
                        userInstance= Room.databaseBuilder<UserDatabase>(context.applicationContext, UserDatabase::class.java, "user_database").build()
                    }
                }
            }
            return userInstance
        }
    }
}*/
