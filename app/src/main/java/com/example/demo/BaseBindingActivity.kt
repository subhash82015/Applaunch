package com.example.demo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nettechnocrats.taxi_dvr_20.interfaces.ToolbarClick


abstract class BaseBindingActivity : AppCompatActivity(), ToolbarClick,
    View.OnClickListener {
    var mActivity: AppCompatActivity? = null
    protected abstract fun setBinding()
    protected abstract fun createActivityObject(savedInstanceState: Bundle?)
    protected abstract fun initializeObject()
    protected abstract fun setListeners()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createActivityObject(savedInstanceState)
        setBinding()
        initializeObject()
        setListeners()

    }

    /*  override fun setToolbarCustomTitle(titleKey: String?) {}*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        try {
            if (mActivity != null)
                Env.currentActivity = mActivity
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun onPause() {
        super.onPause()
    }


    override fun onClick(view: View) {}
    /* override fun showUpIconVisibility(isVisible: Boolean) {
         val actionBar = this.supportActionBar
         actionBar!!.setDisplayHomeAsUpEnabled(isVisible)
     }*/



}