package com.example.mate

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: SqliteHelper
    lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = SqliteHelper(this, "todo.db", null, 1)
        database = dbHelper.writableDatabase

        clickButton.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Add::class.java)
            startActivity(intent)
        }

        clickButton2.setOnClickListener {
            val intent = Intent(this, Popup_Todo_Update::class.java)
            startActivity(intent)
        }

        //Pop_Todo_act에서 반환한 텍스트 받아서 textView1.text에 대입하기. //그러나 아직 다른 액티비티 사이에서 정보 공유를 어떻게 해야하는지 모름.

        /*

        val list = helper.selectTodo()
        textView1.text = list[0].tName

         */
    }
}