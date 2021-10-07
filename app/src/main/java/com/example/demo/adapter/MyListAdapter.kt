package com.example.demo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.interfaces.ItemRowClick
import com.example.demo.viewmodel.HomeViewModel
import com.nettechnocrats.taxi_dvr_20.interfaces.ToolbarClick

class MyListAdapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val email: Array<String>): RecyclerView.Adapter<MyListAdapter.ViewHolder>()
{
    var itemRowClick: ItemRowClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent, false))

    override fun getItemCount() = id.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.idText.text = "ID: ${id[position]}"
        holder.nameText.text = "Name: ${name[position]}"
        holder.emailText.text = "Email: ${email[position]}"
        holder.ly_item.setOnClickListener {
            itemRowClick?.onItemClickListner(2)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idText = itemView.findViewById(R.id.textViewId) as TextView
        val nameText = itemView.findViewById(R.id.textViewName) as TextView
        val emailText = itemView.findViewById(R.id.textViewEmail) as TextView
        val ly_item = itemView.findViewById(R.id.ly_item) as LinearLayout
    }
}