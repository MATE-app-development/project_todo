package com.example.mate

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper= SqliteHelper(this, "todo.db", null, 1)
        //onCreate에서만 context를 this로 줄 수 있음.
        setContentView(R.layout.activity_main)

        var selecteddate = LocalDate.now()
        editTextDate.text = selecteddate.toString()

        var resulttodolist = dbHelper.selectalltodo(dbHelper.readableDatabase,null)
        textView1.text = resulttodolist[0].tID.toString()
        textView2.text = resulttodolist[1].tID.toString()
        textView3.text = resulttodolist[2].tID.toString()
        textView4.text = resulttodolist[3].tID.toString()
        textView5.text = resulttodolist[4].tID.toString()
        textView6.text = resulttodolist[5].tID.toString()

        clickButton.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Add::class.java)
            startActivity(intent)
        }

        textView1.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Update::class.java)
            intent.putExtra("tID", textView1.text)
            startActivity(intent)
        }
    }
}