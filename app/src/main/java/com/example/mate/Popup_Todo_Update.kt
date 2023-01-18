package com.example.mate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_popup_todo_add.*
import kotlinx.android.synthetic.main.activity_popup_todo_update.*
import kotlinx.android.synthetic.main.activity_popup_todo_update.alram_todo
import kotlinx.android.synthetic.main.activity_popup_todo_update.cancel_button_todo
import kotlinx.android.synthetic.main.activity_popup_todo_update.date_todo
import kotlinx.android.synthetic.main.activity_popup_todo_update.deadline_todo

class Popup_Todo_Update : AppCompatActivity() {
    private var TDATE_REQUSET_CODE = 1
    private var TDLINE_REQUSET_CODE = 2
    private var TALRAM_REQUSET_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_todo_update)

        dbHelper = SqliteHelper(this, "todo.db", null, 1)
        database = dbHelper.writableDatabase

        date_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner::class.java)
            startActivityForResult(intent, TDATE_REQUSET_CODE)
        }

        deadline_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner_Before_Time::class.java)
            startActivityForResult(intent, TDLINE_REQUSET_CODE)
        }

        alram_todo.setOnClickListener {
            val intent = Intent(this, DatePick_Spinner_Before_Time::class.java)
            startActivityForResult(intent, TALRAM_REQUSET_CODE)
        }

        cancel_button_todo.setOnClickListener {
            this.finish()
        }

        delete_button_todo.setOnClickListener {
            Toast.makeText(this.applicationContext, "할 일을 삭제했습니다.", Toast.LENGTH_SHORT).show()
            this.finish()
        }

        update_button_todo.setOnClickListener {
            this.finish()
        }

    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TDATE_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_date= data?.getStringExtra("chosendate").toString()
                date_todo.text = t_date
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
            }
        }
        else if (requestCode == TDLINE_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_dline= data?.getStringExtra("chosendatetime").toString()
                deadline_todo.text = t_dline
                //시간이 안 뜸 개빡침
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
                t_dline=null
            }
        }
        else if (requestCode == TALRAM_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_alarm= data?.getStringExtra("chosendatetime").toString()
                alram_todo.text = t_alarm
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}