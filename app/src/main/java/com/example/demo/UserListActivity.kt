package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.adapter.MyListAdapter
import com.example.demo.data.DatabaseHandler
import com.example.demo.data.UserModelClass
import com.example.demo.databinding.ActivityLoginBinding
import com.example.demo.databinding.ActivityUserListBinding
import com.example.demo.interfaces.ItemRowClick
import com.example.demo.viewmodel.HomeViewModel
import com.example.demo.viewmodel.LoginViewModel

class UserListActivity : BaseBindingActivity() {
    private lateinit var binding: ActivityUserListBinding
    private var viewModel: HomeViewModel? = null
    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, HomeViewModel.Factory(this))
            .get(HomeViewModel::class.java)
        binding.viewModel = viewModel

    }

    override fun createActivityObject(savedInstanceState: Bundle?) {
        mActivity = this
    }

    override fun initializeObject() {
        if (viewModel?.loginType?.value == 0)
            fetchUserList()
    }


    fun fetchUserList() {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val emp: List<UserModelClass> = databaseHandler.viewUser()
        val empArrayId = Array<String>(emp.size) { "0" }
        val empArrayName = Array<String>(emp.size) { "null" }
        val empArrayEmail = Array<String>(emp.size) { "null" }
        var index = 0
        for (e in emp) {
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.fname + " " + e.lname
            empArrayEmail[index] = e.userEmail
            index++
        }
        val manager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = manager
        val myListAdapter = MyListAdapter(this, empArrayId, empArrayName, empArrayEmail)
        binding.rvUserList.adapter = myListAdapter

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                myListAdapter.notifyItemRemoved(pos)
                databaseHandler.deleteUser(pos);
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvUserList)

    }


    override fun setListeners() {
    }

    override fun onLeftBtnClick(view: View) {
        finish()
    }

    override fun onRightBtnClick(view: View) {
    }

}