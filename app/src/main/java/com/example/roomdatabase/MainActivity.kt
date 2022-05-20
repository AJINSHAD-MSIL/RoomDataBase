package com.example.roomdatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val users: MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "User Details"
        var counter = 0
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "ajin"
        ).fallbackToDestructiveMigration().build()
        val dbDao = db.userDao()
        val firstname = findViewById<EditText>(R.id.firstname)
        val lastname = findViewById<TextView>(R.id.lastname)
        val resultTextView = findViewById<TextView>(R.id.result)

        findViewById<Button>(R.id.runButton).setOnClickListener {
            lifecycleScope.launch {
                dbDao.deleteAll()
                users.add(User(++counter, firstname.text.toString(), lastname.text.toString()))
                dbDao.insert(users)
                resultTextView.text = "done"

            }
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            lifecycleScope.launch {
                dbDao.deleteAll()
                resultTextView.text = "done"

            }
        }

        findViewById<Button>(R.id.showButton).setOnClickListener {
            lifecycleScope.launch {
                dbDao.getAll().let {
                    resultTextView.text = it.toString()
                    Toast.makeText(applicationContext, it.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}