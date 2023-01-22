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
import kotlinx.android.synthetic.main.activity_popup_todo_update.rpt_todo
import kotlinx.android.synthetic.main.activity_popup_todo_update.view.*

class Popup_Todo_Update : AppCompatActivity() {

    var t_date: String = ""
    var t_dline: String? = ""
    var t_alarm: String? = ""
    var td_name: String? = ""
    var trpt: Boolean = false

    private var TDATE_REQUSET_CODE = 1
    private var TDLINE_REQUSET_CODE = 2
    private var TALRAM_REQUSET_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_todo_update)
        dbHelper = SqliteHelper(this, "todo.db", null, 1)

        val tIDb = intent.getStringExtra("tID")
        val tID = (tIDb.toString()).toInt()

        var condition = "Todo.tID = ${tID}"
        var thetodo = dbHelper.selectoneTodo(dbHelper.readableDatabase, condition) //이 문장이 오류임.

        td_name = thetodo.tName
        t_date = thetodo.tdate.toString()
        t_dline = thetodo.tdline
        t_alarm = thetodo.talarm
        trpt = thetodo.trpt == true

        todoName_todo_update.setText(td_name)
        date_todo.text = t_date
        deadline_todo.text = t_dline
        alram_todo.text = t_alarm
        rpt_todo.isChecked = trpt

        //처음 초기화는 잘 되는데, 값을 수정하니까 리셋되어버림.... ㅠㅠ 다시! (이름 말고 날짜가 문제임.)

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

        update_button_todo.setOnClickListener {
            if(todoName_todo_update.text.toString().isNotEmpty() && t_date.isNotEmpty()){
                td_name= todoName_todo_update.text.toString()

                if(rpt_todo.isChecked){
                    trpt = true
                }

                var thetd = todo(tID, td_name, t_date, t_dline, null, trpt, t_alarm, null, null)
                if(dbHelper.updateTodo(dbHelper.writableDatabase, thetd) == 1)
                    Toast.makeText(this.applicationContext, "할일을 수정했습니다.", Toast.LENGTH_SHORT).show()
            }
            this.finish()
        }

        delete_button_todo.setOnClickListener {
            dbHelper.deleteTodo(dbHelper.writableDatabase, tID)
            Toast.makeText(this.applicationContext, "할 일을 삭제했습니다.", Toast.LENGTH_SHORT).show()
            this.finish()
        }

        cancel_button_todo.setOnClickListener {
            this.finish()
        }
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setContentView(R.layout.activity_popup_todo_update)

        if (requestCode == TDATE_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_date = data?.getStringExtra("chosendate").toString()
                date_todo.text = t_date
            }
        } else if (requestCode == TDLINE_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_dline = data?.getStringExtra("chosendatetime").toString()
                deadline_todo.text = t_dline
            }
        } else if (requestCode == TALRAM_REQUSET_CODE) {
            if (resultCode == RESULT_OK) {
                t_alarm = data?.getStringExtra("chosendatetime").toString()
                alram_todo.text = t_alarm
            }
        }
    }
}