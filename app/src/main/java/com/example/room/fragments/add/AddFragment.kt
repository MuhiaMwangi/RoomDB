package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.data.User
import com.example.room.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//1.When we press add button, we want it to add the items in the field to the our userDatabase.Here is how we go about it:

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_button.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val surname = addSurname_et.text.toString()
        val age = age_et.text


        //If the input fields of fistName,surname and age are filled correctly, create a user object and then later add that object to the db using mUserViewModel
        if (inputCheck(firstName, surname, age)) {
            //create User object
            val user = User(0, firstName, surname, Integer.parseInt(age.toString()))
            //Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_LONG).show()
        }
    }

    //We are checking that the fields are not empty
    //If the fields are not empty, we return a boolean value: true
    private fun inputCheck(firstName: String, surname: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(surname) && age.isEmpty())
    }
}