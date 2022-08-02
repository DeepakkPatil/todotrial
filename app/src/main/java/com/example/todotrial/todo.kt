package com.example.todotrial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todotrial.databinding.ActivityTodoBinding
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class todo  : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<usertodo>

    val today= LocalDate.now()

    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
//    val date= Calendar.getInstance()
//
//    val year=date.get(Calendar.YEAR).toString()
//    val datee= date.get(Calendar.DATE).toString()
    val d ="weektodo/"+tomorrow.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<usertodo>()
        getUserData()



    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("weektodo/"+today)

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(usertodo::class.java)
                        userArrayList.add(user!!)

                    }


                    userRecyclerview.adapter = todoadapter(userArrayList)



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}