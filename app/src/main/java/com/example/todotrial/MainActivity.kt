package com.example.todotrial

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)


//        val headerView : HeaderView = findViewById(R.id.nav_view)

//        val email =intent.getStringExtra("email")
//        val headerView : View = navView.getHeaderView(0)
//        val navUsername : TextView = headerView.findViewById(R.id.username)
//        val navUserEmail : View = headerView.findViewById(R.id.useremailid)


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_todo -> {
                    val Intent = Intent(this, addpersoneltodo::class.java)

                    startActivity(Intent)

                }

                R.id.step_counter->{

                    val Intent = Intent(this, stepcounter::class.java)

                    startActivity(Intent)

                }

                R.id.nav_exercise -> {
//                    val date= Calendar.getInstance()

//                    val yesterday = LocalDate.now().plus(-1, ChronoUnit.DAYS)
////                    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS)
////                    val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("EE, MMM d, yyy"))
//                    Toast.makeText(applicationContext, yesterday.toString(), Toast.LENGTH_SHORT).show()
//                    Toast.makeText(applicationContext, date.get(Calendar.DAY_OF_WEEK).toString(), Toast.LENGTH_SHORT).show()
//                    val Intent = Intent(this, personeltodo::class.java)
//
//                    startActivity(Intent)


//                    val date = Calendar.getInstance()
//                    val dayofweek = date.get(Calendar.DAY_OF_WEEK).toString()

                    val today = LocalDate.now()
                    Toast.makeText(applicationContext, "Submitted successfully1", Toast.LENGTH_SHORT).show()

//                    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS).toString()
                    val a = Intent(this, personeltodo::class.java)
                    startActivity(a)
                        database = FirebaseDatabase.getInstance().getReference("personaltodo/"+today+"/3")
                        val array= database.child("title").get().addOnSuccessListener {

//                            Toast.makeText(applicationContext, , Toast.LENGTH_SHORT).show()
//                            if (it.exists()) {
//                                var percent = it.child("description").value.toString().toInt()
//                                var ismarked = it.child("ismarked").value.toString()
//                                var date = it.child("date").value.toString()
//
//                                var name= arrayOf(it.child("title").value)
//
//                                if ("Mango" in name){
//                                    Toast.makeText(applicationContext, "Submitted 2successfully mango", Toast.LENGTH_SHORT).show()
//
//                                }
////                                if (percent+10>=100) {
////                                    percent=100
//////                                    percent = percent + 10
////
////                                    if (percent.toInt()==100) {
////
////                                    ismarked="T"
////
////                                    }
////                                }
//
////                                database = FirebaseDatabase.getInstance().getReference("weektodo/"+today.toString())
////                                val attendance = usertodo(today.toString(),name, percent.toString(),ismarked)
////
////                                database.child(name).setValue(attendance).addOnSuccessListener {
////                                    Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
////                                    val a = Intent(this, todo::class.java)
////                                    startActivity(a)
////                                }
//
//                            }

                        }

                }
                R.id.nav_addtodo -> {

                    val Intent = Intent(this, addtodo::class.java)

                    startActivity(Intent)

                }
                R.id.nav_sports-> {
                    val date = Calendar.getInstance()
                    val dayofweek = date.get(Calendar.DAY_OF_WEEK).toString()

                    val today = LocalDate.now()
                    Toast.makeText(applicationContext, "Submitted successfully1", Toast.LENGTH_SHORT).show()

                    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS).toString()
                    if (dayofweek.toInt() == 7)
                    {    Toast.makeText(applicationContext, "Submitted 2successfully", Toast.LENGTH_SHORT).show()

                        database = FirebaseDatabase.getInstance().getReference("weektodo/"+today)
                        database.child("exercise").get().addOnSuccessListener {

                            if (it.exists()) {
                                var percent = it.child("percent").value.toString().toDouble()
                                var ismarked = it.child("ismarked").value.toString()
                                var date = it.child("date").value.toString()

                                var name= it.child("name").value.toString()
                                percent= percent/10

                                database = FirebaseDatabase.getInstance().getReference("weektodo/"+tomorrow.toString())
                                val attendance = usertodo(tomorrow.toString(),name, percent.toString(),ismarked)

                                database.child(name).setValue(attendance).addOnSuccessListener {
                                    Toast.makeText(applicationContext, "Submitted successfully", Toast.LENGTH_SHORT).show()
                                    val a = Intent(this, todo::class.java)
                                    startActivity(a)
                                }

                            }

                        }
                            }
                }




            }

            true


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){

            return true


        }
        return super.onOptionsItemSelected(item)
    }

}