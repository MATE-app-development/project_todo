package com.example.mate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_popup_todo_update.*

class Popup_Todo_Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_todo_update)

        date_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner::class.java)
            startActivity(intent)
        }

        deadline_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner_Before_Time::class.java)
            startActivity(intent)
        }

        alram_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner_Before_Time::class.java)
            startActivity(intent)
        }

        cancel_button_todo.setOnClickListener {
            this.finish()
        }

        delete_button_todo.setOnClickListener {
            this.finish()
        }

        update_button_todo.setOnClickListener {
            this.finish()
        }
    }
}