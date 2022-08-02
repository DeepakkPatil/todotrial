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

class addtodo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var firebaseUserID: String = ""
    private lateinit var subButton : Button
    lateinit var Name : EditText

    lateinit var intrest : EditText
    private lateinit var percent : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtodo)

        auth = FirebaseAuth.getInstance()

        subButton = findViewById(R.id.submitbutton)
        Name = findViewById(R.id.name)
        intrest = findViewById(R.id.rank)
        percent = findViewById(R.id.percent)


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
                    val tomorrow = LocalDate.now()

//                    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        val intrest: String = intrest.text.toString()
        val Name: String = Name.text.toString()
        val percent: String=percent.text.toString()
        val ismark:String="True"
        if (Name.isBlank() || percent.isBlank()  ) {
            Toast.makeText(this, "All fields are compulsory.", Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this,percent,Toast.LENGTH_SHORT).show()

//val d= datee+year.toString()
        database = FirebaseDatabase.getInstance().getReference("weektodo/"+tomorrow.toString())
        val attendance = usertodo(tomorrow.toString(),Name, percent,intrest,ismark)

        database.child(Name).setValue(attendance).addOnSuccessListener {
            Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
            val a = Intent(this, todo::class.java)
            startActivity(a)
        }


    }}