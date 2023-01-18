package com.example.mate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*
import kotlinx.android.synthetic.main.activity_date_pick_spinner_before_time.*
import kotlinx.android.synthetic.main.activity_date_pick_spinner_before_time.datePicker
class DatePick_Spinner_Before_Time : AppCompatActivity() {
    var date =""
    var chosentime:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_pick_spinner_before_time)

        fun month_to_twodig(themonth:String): String {
            if ("1" <= themonth && themonth < "10") {
                return StringBuilder("0").append(themonth).toString();
            }
            else { return themonth}
        }

        button_date_to_time.setOnClickListener {
            date = datePicker.year.toString()+"-"+month_to_twodig((datePicker.month+1).toString())+"-"+datePicker.dayOfMonth.toString()

            val intent2 = Intent(this, TimePick_Spinner::class.java).putExtra("chosendate", date)
            startActivityForResult(intent2, 1)
        }

        button_date_quit.setOnClickListener {
            date = datePicker.year.toString()+"-"+month_to_twodig((datePicker.month+1).toString())+"-"+datePicker.dayOfMonth.toString()
            intent.putExtra("chosendatetime", date)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                chosentime = data?.getStringExtra("chosentime").toString()
                date=date+" "+chosentime
                intent.putExtra("chosendatetime", date)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this.applicationContext, "날짜 입력 실패", Toast.LENGTH_SHORT).show()
                intent.putExtra("chosendatetime", date)
                finish()
            }
        }
    }
}