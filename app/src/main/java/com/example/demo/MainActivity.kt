package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var  tv_login:TextView
    lateinit var  btn_signup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_login=findViewById<TextView>(R.id.tv_login)
        btn_signup=findViewById<TextView>(R.id.btn_signup)
        tv_login.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java).putExtra("page",1))
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java).putExtra("page",0))
        }
    }
}