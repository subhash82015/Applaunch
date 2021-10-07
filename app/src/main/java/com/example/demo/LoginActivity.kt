package com.example.demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.demo.databinding.ActivityLoginBinding
import com.example.demo.viewmodel.LoginViewModel

class LoginActivity : BaseBindingActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var viewModel: LoginViewModel? = null
    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, LoginViewModel.Factory(this))
            .get(LoginViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun createActivityObject(savedInstanceState: Bundle?) {
        mActivity = this
    }

    override fun initializeObject() {
        viewModel?.loginType?.value = intent.getIntExtra("page", 1)
        if (viewModel?.loginType?.value == 1){
            viewModel?.page?.value = "Login Here"
            viewModel?.btn_submit?.value = "Login"}
        else{
            viewModel?.page?.value = "Signup Here"
            viewModel?.btn_submit?.value = "Signup"}
    }

    override fun setListeners() {
    }

    override fun onLeftBtnClick(view: View) {
        finish()
    }
    override fun onRightBtnClick(view: View) {
    }


}