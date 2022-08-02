package com.example.todotrial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todotrial.databinding.ActivityPersoneltodoBinding
import com.example.todotrial.databinding.ActivityTodoBinding
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class personeltodo  : AppCompatActivity() {

    private lateinit var binding: ActivityPersoneltodoBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<ptodo>

    val today= LocalDate.now()

    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
    //    val date= Calendar.getInstance()
//
//    val year=date.get(Calendar.YEAR).toString()
//    val datee= date.get(Calendar.DATE).toString()
    val d ="weektodo/"+tomorrow.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityPersoneltodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<ptodo>()
        getUserData()



    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("personaltodo/"+today)

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(ptodo::class.java)
                        userArrayList.add(user!!)

                    }


                    userRecyclerview.adapter = todoadapterp(userArrayList)



                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}