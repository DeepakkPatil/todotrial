package com.example.todotrial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class addpersoneltodo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var subButton : Button
    lateinit var title : EditText
    private lateinit var desc : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpersoneltodo)

        auth = FirebaseAuth.getInstance()

        subButton = findViewById(R.id.submitbutton)
        title= findViewById(R.id.title)
        desc= findViewById(R.id.desc)


        subButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
//        val date= Calendar.getInstance()
//
//        val year=date.get(Calendar.YEAR).toString()
//         val datee= date.get(Calendar.DATE).toString()
//
                    val today = LocalDate.now()

        val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        val title :String= title.text.toString()
        val description: String=desc.text.toString()
        val ismark:String="True"
        if (title.isBlank() || description.isBlank()  ) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
//        Toast.makeText(this,title,Toast.LENGTH_SHORT).show()

//val d= datee+year.toString()
        database = FirebaseDatabase.getInstance().getReference("personaltodo/"+today.toString())
        val attendance = ptodo(today.toString(),title.toString(), description,ismark)

        database.child(description).setValue(attendance).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
            val a = Intent(this, personeltodo::class.java)
            startActivity(a)
        }


    }}