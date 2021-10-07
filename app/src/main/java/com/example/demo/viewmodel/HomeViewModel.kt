package com.example.demo.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.UserListActivity
import com.example.demo.data.DatabaseHandler
import com.example.demo.data.UserModelClass
import com.example.demo.interfaces.ItemRowClick

class HomeViewModel(val context: Context) : ViewModel() {

    var loginType: MutableLiveData<Int>? = null
    var mobile: MutableLiveData<String>? = null
    var email: MutableLiveData<String>? = null
    var page: MutableLiveData<String>? = null
    var btn_submit: MutableLiveData<String>? = null
    var fname: MutableLiveData<String>? = null
    var lname: MutableLiveData<String>? = null

    init {
        loginType = MutableLiveData()
        loginType?.value = 0
        mobile = MutableLiveData("")
        email = MutableLiveData("")
        page = MutableLiveData("")
        btn_submit = MutableLiveData("")
        fname = MutableLiveData("")
        lname = MutableLiveData("")
        page?.value="User List"

    }
    fun onAddClick(view: View) {
        loginType?.value = 1
        page?.value="Add User"
    }
    fun onSaveClick(view: View) {
        if (fname?.value?.isEmpty() == true)
            Toast.makeText(context as UserListActivity,"First Name is Required",Toast.LENGTH_SHORT).show()
        else if (lname?.value?.isEmpty() == true)
            Toast.makeText(context as UserListActivity,"Last Name is Required",Toast.LENGTH_SHORT).show()
        else if (email?.value?.isEmpty() == true)
            Toast.makeText(context as UserListActivity,"Email is Required",Toast.LENGTH_SHORT).show()
        else
            HitAddUser()

    }
 fun onCancelClick(view: View) {
     fname?.value=""
     lname?.value=""
     email?.value=""
    }

    fun HitAddUser() {
        val id = "2"
        val databaseHandler: DatabaseHandler = DatabaseHandler(context as UserListActivity)
        if (fname?.value?.trim() != "" && lname?.value?.trim() != "" && email?.value?.trim() != "") {
            val status =
                databaseHandler.addUser(UserModelClass(Integer.parseInt(id), fname?.value.toString(),lname?.value.toString(),
                    "", email?.value.toString()))
            if (status > -1) {
                Toast.makeText(context as UserListActivity, "User added", Toast.LENGTH_LONG).show()
                loginType?.value = 0
            }

        } else {
            Toast.makeText(
                context as UserListActivity,
                "Blank forn not allow",
                Toast.LENGTH_LONG
            ).show()
        }

    }


    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return HomeViewModel(
                context
            ) as T
        }
    }


}