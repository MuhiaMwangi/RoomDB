package com.example.room.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


//viewModel provides data to the UI and survive configuration changes
//A viewModel acts as a communication center between the repository and the UI


@InternalCoroutinesApi
class UserViewModel(application:Application): AndroidViewModel(application)
{
    val readAllData:LiveData<List<User>>
  private val repository: UserRepository

  //init block is the first to be executed  when UserViewModel is called
init {
    val userDao= UserDatabase.getDatabase(application).userDao()
      //we are using our database to call its getDatabase method
      //we are then accessing our userDao-(The one that we associated to the UserDatabase-"abstract fun userDao")
    repository= UserRepository(userDao)
    readAllData= repository.readAllData

}
    fun addUser(user:User)
    {
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user)

        }
    }
}