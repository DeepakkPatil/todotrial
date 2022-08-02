package com.example.todotrial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class todoadapter(private val userList : ArrayList<usertodo> ) : RecyclerView.Adapter<todoadapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        userList.sortBy {
            it.intrest?.toInt()
            it.percent?.toInt()
        }
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list,
            parent,false)


        return MyViewHolder(itemView)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

//        holder.cname.text = currentitem.cname
//if (currentitem.name=)

        holder.name.text = currentitem.name

        holder.percent.text = currentitem.intrest

        holder.tv.text = currentitem.percent

        holder.ismarked.text = currentitem.ismarked
        val t = Timer()
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                holder.pb!!.progress = currentitem.percent!!.toInt()


            }
        }
        t.schedule(tt, 0, 10)

    }

    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //        val cname : TextView = itemView.findViewById(R.id.cname)
        val name : TextView = itemView.findViewById(R.id.tvfirstName)

        val percent : TextView = itemView.findViewById(R.id.percent)

        val ismarked : TextView = itemView.findViewById(R.id.ismarked)

        val pb : ProgressBar = itemView.findViewById(R.id.pb) as ProgressBar


        val tv : TextView = itemView.findViewById(R.id.tv)






    }


}