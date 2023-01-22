package com.example.mate

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View.OnClickListener
import android.view.View.OnDragListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*
import kotlinx.android.synthetic.main.activity_popup_todo_add.*
import java.util.SimpleTimeZone

lateinit var dbHelper: SqliteHelper
lateinit var database: SQLiteDatabase


class Popup_Todo_Add: AppCompatActivity() {

    var t_date:String =""
    var t_dline:String? =""
    var t_alarm:String? =""
    var td_name:String?=""
    var trpt:Boolean=false

    private var TDATE_REQUSET_CODE = 1
    private var TDLINE_REQUSET_CODE = 2
    private var TALRAM_REQUSET_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_todo_add)

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


        add_button_todo.setOnClickListener {
            if(todoName_todo.text.toString().isNotEmpty() && t_date.isNotEmpty()){ //필수 입력 칸 : 이름과 날짜. 그런데 날짜는 입력 안 하면 자동으로 오늘 날짜 입력됨.
                td_name= todoName_todo.text.toString()

                if(rpt_todo.isChecked){
                    trpt = true
                }

                var thetd = todo(null, td_name, t_date, t_dline, null, trpt, t_alarm, null, null)
                if(dbHelper.insertTodo(thetd) == 1)
                    Toast.makeText(this.applicationContext, "할일을 입력했습니다.", Toast.LENGTH_SHORT).show()
            }
            this.finish()
        }

        cancel_button_todo.setOnClickListener {
            Toast.makeText(this.applicationContext, "할 일 입력을 취소했습니다.", Toast.LENGTH_SHORT).show()
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
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
                t_dline=""
            }
        }
        else if (requestCode == TALRAM_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_alarm= data?.getStringExtra("chosendatetime").toString()
                alram_todo.text = t_alarm
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
                t_alarm=""
            }
        }
    }
}
