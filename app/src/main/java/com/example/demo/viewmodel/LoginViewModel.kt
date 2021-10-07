package com.example.demo.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.LoginActivity
import com.example.demo.UserListActivity
import com.example.demo.data.DatabaseHandler
import com.example.demo.data.UserModelClass

class LoginViewModel(val context: Context) : ViewModel() {
    var loginType: MutableLiveData<Int>? = null
    var mobile: MutableLiveData<String>? = null
    var email: MutableLiveData<String>? = null
    var page: MutableLiveData<String>? = null
    var btn_submit: MutableLiveData<String>? = null
    var fname: MutableLiveData<String>? = null
    var lname: MutableLiveData<String>? = null
    var password: MutableLiveData<String>? = null

    init {
        loginType = MutableLiveData()
        loginType?.value = 1
        mobile = MutableLiveData("")
        email = MutableLiveData("")
        page = MutableLiveData("")
        btn_submit = MutableLiveData("")
        fname = MutableLiveData("")
        lname = MutableLiveData("")
        password = MutableLiveData("")

    }
    fun onContinueClick(view: View) {
        if (loginType?.value==1)
        {
            if (email?.value?.isEmpty() == true)
                Toast.makeText(context as LoginActivity,"Email is Required",Toast.LENGTH_SHORT).show()
            else if (password?.value?.isEmpty() == true)
            Toast.makeText(context as LoginActivity,"Password is Required",Toast.LENGTH_SHORT).show()
            else
                HitLogin()
        }
        else
        {
            if (fname?.value?.isEmpty() == true)
                Toast.makeText(context as LoginActivity,"First Name is Required",Toast.LENGTH_SHORT).show()
           else if (lname?.value?.isEmpty() == true)
                Toast.makeText(context as LoginActivity,"Last Name is Required",Toast.LENGTH_SHORT).show()
            else if (email?.value?.isEmpty() == true)
                Toast.makeText(context as LoginActivity,"Email is Required",Toast.LENGTH_SHORT).show()
            else if (password?.value?.isEmpty() == true)
                Toast.makeText(context as LoginActivity,"Password is Required",Toast.LENGTH_SHORT).show()
            else
                HitSignup()
        }

    }

    fun HitLogin()
    {
        val databaseHelper: DatabaseHandler = DatabaseHandler(context as LoginActivity)

        if (databaseHelper!!.checkUser(email?.value?.toString()!!,password?.value?.toString()!!)) {
            context.startActivity(Intent(context as LoginActivity, UserListActivity::class.java))
        }
        else
        {
            Toast.makeText(context as LoginActivity,"Invalid account",Toast.LENGTH_SHORT).show()
        }
    }

    fun HitSignup() {
        val id = "2"
        val databaseHandler: DatabaseHandler = DatabaseHandler(context as LoginActivity)
        if (fname?.value?.trim() != "" && lname?.value?.trim() != "" && email?.value?.trim() != "" && password?.value?.trim() != "") {
            val status =
                databaseHandler.addUser(UserModelClass(Integer.parseInt(id), fname?.value.toString(),lname?.value.toString(),
                    password?.value.toString(), email?.value.toString()))
            if (status > -1) {
                Toast.makeText(context as LoginActivity, "User Registered", Toast.LENGTH_LONG).show()
                context.startActivity(Intent(context as LoginActivity, UserListActivity::class.java))

            }

        } else {
            Toast.makeText(
                context as LoginActivity,
                "Blank forn not allow",
                Toast.LENGTH_LONG
            ).show()
        }

    }


    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return LoginViewModel(
                context
            ) as T
        }
    }
}