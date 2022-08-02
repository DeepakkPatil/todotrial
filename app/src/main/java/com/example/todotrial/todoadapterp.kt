package com.example.todotrial

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate

class todoadapterp(private val userList : ArrayList<ptodo> ) : RecyclerView.Adapter<todoadapterp.MyViewHolder>() {


    private lateinit var database : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        userList.sortBy { it.percent?.toInt() }
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list2,
            parent,false)



        return MyViewHolder(itemView)



    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

//        holder.cname.text = currentitem.cname
//if (currentitem.name=)

        holder.name.text = currentitem.title

        holder.percent.text = currentitem.descripton

        holder.ismarked.text = currentitem.ismarked

        holder.btndelete.setOnClickListener() {
            val today= LocalDate.now()
            currentitem.ismarked="false"

            database = FirebaseDatabase.getInstance().getReference("personaltodo/"+today.toString())
            val at= ptodo(today.toString(),currentitem.title, currentitem.descripton,currentitem.ismarked)

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void OnClick(View view) {
//                    Toast.makeText(view.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show();
//                }
            database.child(currentitem.title.toString()+currentitem.descripton.toString()).removeValue().addOnSuccessListener {
//                Toast.makeText( "Submitted successfully", Toast.LENGTH_SHORT).show()
//                val a = Intent(context, todo::class.java)
//                startActivity()

            }

        }


    }

    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //        val cname : TextView = itemView.findViewById(R.id.cname)
        val name : TextView = itemView.findViewById(R.id.tvfirstName)

        val percent : TextView = itemView.findViewById(R.id.percent)

        val ismarked : TextView = itemView.findViewById(R.id.ismarked)

        val btndelete : Button = itemView.findViewById(R.id.btndelete)






    }


}