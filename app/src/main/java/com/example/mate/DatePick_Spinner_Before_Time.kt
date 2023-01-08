package com.example.mate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_date_pick_spinner.*
import kotlinx.android.synthetic.main.activity_date_pick_spinner_before_time.*
import kotlinx.android.synthetic.main.activity_date_pick_spinner_before_time.datePicker

class DatePick_Spinner_Before_Time : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_pick_spinner_before_time)

        button_date_to_time.setOnClickListener {
            var date = datePicker.year.toString()+"/"+datePicker.month.toString()+"/"+datePicker.dayOfMonth.toString()

            val intent = Intent(this, TimePick_Spinner::class.java)
            startActivity(intent)
            finish()
        }
    }
}