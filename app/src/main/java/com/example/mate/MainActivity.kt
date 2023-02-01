package com.example.mate

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = SqliteHelper(this, "todo.db", null, 1)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        var selecteddate = LocalDate.now()
        editTextDate.text = selecteddate.toString()

        var resulttodolist = dbHelper.selectalltodo(dbHelper.readableDatabase, null)
        textView1.text = resulttodolist[0].tID.toString()

        clickButton.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Add::class.java)
            startActivity(intent)
        }

        textView1.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Update::class.java)
            intent.putExtra("tID", textView1.text.toString())
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        var selecteddate = LocalDate.now()
        editTextDate.text = selecteddate.toString()

        var resulttodolist = dbHelper.selectalltodo(dbHelper.readableDatabase, null)
        textView1.text = resulttodolist[0].tID.toString()

        clickButton.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Add::class.java)
            startActivity(intent)
        }

        textView1.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Update::class.java)
            intent.putExtra("tID", textView1.text.toString())
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
